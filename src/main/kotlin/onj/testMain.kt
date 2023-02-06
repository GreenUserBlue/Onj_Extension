package onj

fun main(args: Array<String>) {
    val ht = TestOnjLexer()
    ht.start(testStr,0, testStr.length,0)
}

var testStr: String = """
        key: "value",
        key2: 'value',
        boolean: true,
        int: 34,
        float: 1.23,
        nullValue: null,



        object: {
            key: "value",
            otherKey: "value",
            nestedObject: {
                key: "value"
            }
        }

/*
a block comment
*/

// a line comment

/*
unterminaded block comment

        ,

        arr: [
            "value", 1, 2, true
        ]


        ,

        "I contain Spaces!": true,
        '123 I start with a number!': true



        ,

        var obj = {
            key: "value"
        };

        var number = 5;

        first: obj,
        second: obj,
        favoriteNumber: number,
        arr: [
            true,
            number,
            obj
        ]


        ,

        var catKeys = {
            type: "cat",
            amountLegs: 4
        };

        pets: [
            {
                name: "Lilly",
                ...catKeys
            },
            {
                name: "Bello",
                type: "dog",
                amountLegs: 4,
            },
            {
                ...catKeys,
                name: "Snowflake"
            }
        ],

        var commonFruits = [ "apple", "orange" ];

        fruitSaladOne: [
            "pear",
            ...commonFruits,
            "blueberry"
        ],
        fruitSaladTwo: [
            "pineapple",
            "strawberry",
            ...commonFruits
        ]



        ,

        var two = 1 + 1;

        five: 1 + two * (2 / 1),
        negative: -two



        ,

        var anInt = 2;

        aFloat: anInt#float,
        aString: anInt#string,
        toFloatToString: anInt#float#string


        ,

        var colors = {
            red: "#ff0000",
            green: "#00ff00",
            blue: "#0000ff"
        };

        // properties of objects can be accessed by using a dot
        // followed by an identifier
        favoriteColor: color.blue,
        // identifiers can be wrapped in quotes
        anotherColor: color."green",

        var animals = [ "cat", "dog", "bird", "fish" ];

        // values of arrays can be accessed by using an integer
        // after the dot
        favoriteAnimal: animals.0, // arrays are zero-indexed

        var outer = {
            nested: {
                arr: [ "a value" ]
            }
        };

        accessingNested: outer.nested.arr.0







        ,

        var indexOffset = 2;
        var arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];

        dynamic: arr.(indexOffset + 4),

        var strings = [ "a", "b", "c", "d" ];
        var object = {
            a: 0,
            b: 1,
            c: 2,
            d: 3
        };

        dynamic2: object.(strings.(object.a))




        ,

        import "path/from/working/dir/file.onj" as imported;

        someValue: imported



        ,

        var paths = [
            "fist/path/file.onj",
            "second/path/file.onj"
        ];

        import (paths.0) as imported;




        four: sqrt(16.0),

        // pow and in are infix functions, meaning you can call them
        // using the following syntax: param1 function param2

        nine: 3 pow 2,
        // But they can be called using the conventional syntax too
        alsoNine: pow(3, 2),

        // the in-function checks if a value is present in an array
        isTrue: 1 in [ 1, 2, 3, 4 ]

    """.trimIndent()
