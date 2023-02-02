package onj.value

import kotlin.reflect.KClass

/**
 * represents a part of the parsed onj-structure
 */
abstract class OnjValue {

    /**
     * the actual value of the element as a kotlin datatype
     */
    abstract val value: Any?

    fun isInt(): Boolean = this is OnjInt
    fun isNull(): Boolean = this is OnjNull
    fun isFloat(): Boolean = this is OnjFloat
    fun isString(): Boolean = this is OnjString
    fun isOnjArray(): Boolean = this is OnjArray
    fun isBoolean(): Boolean = this is OnjBoolean
    fun isOnjObject(): Boolean = this is OnjObject


    override fun equals(other: Any?): Boolean {
        return other != null && other::class == this::class && (other as OnjValue).value == this.value
    }

    override fun hashCode(): Int = value.hashCode()

    /**
     * converts the structure to a valid onj string. (variables, comments, etc. are lost)
     */
    abstract override fun toString(): String
    abstract fun toString(indentationLevel: Int): String

    /**
     * converts the structure to a valid json-string
     */
    abstract fun toJsonString(): String
    abstract fun toJsonString(indentationLevel: Int): String
}

/**
 * represents an Int (kotlin type is [Long])
 */
class OnjInt(override val value: Long) : OnjValue() {

    override fun toString(): String = toString(0)

    override fun toString(indentationLevel: Int): String {
        return value.toString()
    }



    override fun toJsonString(indentationLevel: Int) = value.toString()
    override fun toJsonString(): String = value.toString()
}

/**
 * represents a Float (kotlin type is [Double])
 */
class OnjFloat(override val value: Double) : OnjValue() {

    override fun toString(): String = toString(0)

    override fun toString(indentationLevel: Int): String {
        return if (value == Double.POSITIVE_INFINITY) "Pos_Infinity"
        else if (value == Double.NEGATIVE_INFINITY) "Neg_Infinity"
        else if (value.isNaN()) "NaN"
        else value.toString()
    }

    override fun toJsonString(indentationLevel: Int): String {
        return if (value == Double.POSITIVE_INFINITY) "Infinity"
        else if (value == Double.NEGATIVE_INFINITY) "-Infinity"
        else if (value.isNaN()) "NaN"
        else value.toString()
    }

    override fun toJsonString(): String = toJsonString(0)
}

/**
 * represents a String
 */
class OnjString(override val value: String) : OnjValue() {

    override fun toString(indentationLevel: Int): String = "'$value'"
    override fun toString(): String = "'$value'"
        .replace("\n", "\\n")
        .replace("\r", "\\r")
    override fun toJsonString(): String = "\"$value\""
        .replace("\n", "\\n")
        .replace("\r", "\\r")
    override fun toJsonString(indentationLevel: Int): String = "\"$value\""
}

/**
 * represents a Boolean
 */
class OnjBoolean(override val value: Boolean) : OnjValue() {

    override fun toString(): String = value.toString()
    override fun toString(indentationLevel: Int): String = value.toString()
    override fun toJsonString(): String = value.toString()
    override fun toJsonString(indentationLevel: Int): String = value.toString()
}

/**
 * represents a null-value
 */
class OnjNull : OnjValue() {

    override val value: Any? = null

    override fun toString(): String = "null"
    override fun toString(indentationLevel: Int): String = "null"
    override fun toJsonString(): String = "null"
    override fun toJsonString(indentationLevel: Int): String = "null"
}

/**
 * represents an object (kotlin type is [Map])
 */
open class OnjObject(override val value: Map<String, OnjValue>) : OnjValue() {

    override fun toString(): String {
        val builder = StringBuilder()
        for (entry in value.entries) {
            val key = if (isValidKey(entry.key)) entry.key else "'${entry.key}'"
            builder
                .append("$key: ")
                .append(entry.value.toString(1))
                .append(",\n")
        }
        return builder.toString()
    }

    override fun toString(indentationLevel: Int): String {
        val builder = StringBuilder()
        builder.append("{")
        builder.append("\n")
        for (entry in value.entries) {
            for (i in 1..indentationLevel) builder.append("    ")
            val key = if (isValidKey(entry.key)) entry.key else "'${entry.key}'"
            builder
                .append("$key: ")
                .append(entry.value.toString(indentationLevel + 1))
                .append(",\n")
        }
        for (i in 1 until indentationLevel) builder.append("    ")
        builder.append("}")
        return builder.toString()
    }

    override fun toJsonString(indentationLevel: Int): String {
        val builder = StringBuilder()
        builder.append("{")
        builder.append("\n")
        val entries = value.entries.toList()
        for (i in entries.indices) {
            for (x in 1..indentationLevel) builder.append("    ")
            val cleanKey = entries[i].key.replace("\n", "").replace("\r", "")
            builder
                .append("\"$cleanKey\": ")
                .append(entries[i].value.toJsonString(indentationLevel + 1))
            if (i != entries.size - 1) builder.append(",")
            builder.append("\n")
        }
        for (i in 1 until indentationLevel) builder.append("    ")
        builder.append("}")
        return builder.toString()
    }

    override fun toJsonString(): String = toJsonString(0)

    operator fun get(identifier: String): OnjValue? = value[identifier]

    /**
     * checks if the object has a key named [key] that has the kotlin or onj type [T]
     */
    inline fun <reified T> hasKey(key: String): Boolean {
        if (!value.containsKey(key)) return false
        return value[key] is T || value[key]?.value is T
    }

    /**
     * checks if the object contains all keys, where String is the name of the key and KClass the
     * (kotlin!) type of the key
     */
    fun hasKeys(keys: Map<String, KClass<*>>): Boolean {
        for (key in keys) {
            if (!key.value.isInstance(value[key.key]?.value)) return false
        }
        return true
    }

    /**
     * gets a value from the object with the key [key] and the type [T].
     * The type can either be the Onj-type or the kotlin-type
     * @throws ClassCastException if the [key] or the type is incorrect
     */
    inline fun <reified T> get(key: String): T {
        return if (value[key]?.value is T) value[key]?.value as T else value[key] as T
    }

    /**
     * checks if the object has the key [key] with type [T] and returns its value. If no such key exists, [or] is
     * returned instead
     */
    inline fun <reified T> getOr(key: String, or: T): T {
        // I hate this code
        return if (value[key]?.value is T) value[key]?.value as T else if (value[key] is T) value[key] as T else or
    }

    /**
     * checks if a key [key] with type [T] exists and if it does, executes [then]. returns the value of [then] or null
     * if it wasn't called
     */
    inline fun <reified T, U> ifHas(key: String, then: (value: T) -> U): U? {
        return if (hasKey<T>(key)) then(value[key] as T) else null
    }

    /**
     * checks if a key [key] with type [T] exists and if it does, executes [then]
     */
    inline fun <reified T> ifHas(key: String, then: (value: T) -> Unit) {
//        if (hasKey<T>(key)) then(get<T>(key))
        if (value[key]?.value is T) then(value[key]?.value as T) else if (value[key] is T) then(value[key] as T)
    }

    private fun isValidKey(key: String): Boolean {
        return Regex("[a-zA-z_][a-zA-z\\d_]*").matches(key)
    }
}

/**
 * represents an Array (kotlin type is [List])
 */
class OnjArray(override val value: List<OnjValue>) : OnjValue() {

    override fun toString(): String = toString(0)

    override fun toString(indentationLevel: Int): String {
        val builder = StringBuilder()
        if (shouldInline()) {
            builder.append("[ ")
            for (part in value) {
                builder
                    .append(part.toString(indentationLevel + 1))
                    .append(", ")
            }
            builder.append("]")
            return builder.toString()
        }

        builder
            .append("[")
            .append("\n")
        for (part in value) {
            for (i in 1..indentationLevel) builder.append("    ")
            builder
                .append(part.toString(indentationLevel + 1))
                .append(",\n")
        }
        for (i in 1 until indentationLevel) builder.append("    ")
        builder.append("]")
        return builder.toString()
    }

    override fun toJsonString(indentationLevel: Int): String {
        val builder = StringBuilder()
        if (shouldInline()) {
            builder.append("[ ")
            for (i in value.indices) {
                builder.append(value[i].toJsonString(indentationLevel + 1))
                if (i != value.size - 1) builder.append(", ")
            }
            builder.append(" ]")
            return builder.toString()
        }

        builder
            .append("[")
            .append("\n")

        for (i in value.indices) {
            for (x in 1..indentationLevel) builder.append("    ")
            builder.append(value[i].toJsonString(indentationLevel + 1))
            if (i != value.size - 1) builder.append(",")
            builder.append("\n")
        }
        for (i in 1 until indentationLevel) builder.append("    ")
        builder.append("]")
        return builder.toString()
    }

    override fun toJsonString() = toJsonString(0)

    /**
     * checks if the array only contains values of type [T]
     */
    inline fun <reified T> hasOnlyType(): Boolean {
        for (part in value) if (part.value !is T) return false
        return true
    }

    /**
     * @return the OnjValue at [index]
     * @throws IndexOutOfBoundsException
     */
    operator fun get(index: Int): OnjValue = value[index]

    /**
     * the size of the array
     */
    fun size(): Int = value.size

    /**
     * gets the value at [index] with type [T] where the type can either be the kotlin-type or the onj-type
     * @throws ClassCastException if the index or the type is incorrect
     */
    inline fun <reified T> get(index: Int): T = if (value[index].value is T) value[index].value as T else value[index] as T

    private fun shouldInline(): Boolean {
        //TODO: this is stupid
        if (value.isEmpty()) return true
        var cost = 0
        for (part in value) {
            if (part.isOnjArray() || part.isOnjObject()) return false
            else if (part.isBoolean()) cost += 5
            else if (part.isInt() || part.isFloat()) cost += 4
            else if (part.isString()) cost += (part as OnjString).value.length
            else if (part.isNull()) cost += 4
        }
        return cost < 60
    }
}

class OnjNamedObject(val name: String, value: Map<String, OnjValue>) : OnjObject(value) {

    override fun toString(): String = toString(0)

    override fun toString(indentationLevel: Int): String {
        return "\$$name ${super.toString(indentationLevel)}"
    }
}
