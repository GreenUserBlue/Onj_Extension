package org.onj.language.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import org.onj.language.psi.OnjTokenSets
import org.onj.language.psi.OnjTypes
import org.onj.language.utils.Utils.iterateOverAstChildren

class OnjBlock(
    private val spacingBuilder: SpacingBuilder,
    node: ASTNode,
    wrap: Wrap?,
    alignment: Alignment?,
    private val indent: Indent?
) : AbstractBlock(node, wrap, alignment) {

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean = node.firstChildNode == null

    override fun buildChildren(): MutableList<Block> {
        val blocks = mutableListOf<Block>()
        var isFistEntry = true // only relevant when inside an object or array
        iterateOverAstChildren(node) { curChild ->

            if (curChild.elementType == TokenType.WHITE_SPACE) return@iterateOverAstChildren

            val block = OnjBlock(
                spacingBuilder,
                curChild,
                getChildWrap(curChild, isFistEntry),
                null,
                getChildIndent(curChild)
            )

            blocks.add(block)

            if (curChild.elementType == OnjTypes.ARRAY_ENTRY || curChild.elementType == OnjTypes.OBJECT_ENTRY) {
                isFistEntry = false
            }
        }
        return blocks
    }

    override fun getIndent(): Indent? {
        return indent
    }

    private fun getChildIndent(childNode: ASTNode): Indent? {
        if (node.elementType == OnjTypes.OBJECT && childNode.elementType !in OnjTokenSets.braces) {
            return Indent.getNormalIndent()
        }

        if (node.elementType == OnjTypes.ARRAY && childNode.elementType !in OnjTokenSets.brackets) {
            return Indent.getNormalIndent()
        }

        return Indent.getNoneIndent()
    }

    private fun getChildWrap(childNode: ASTNode, isFirstChild: Boolean): Wrap? {
        if (childNode.elementType == OnjTypes.ARRAY_ENTRY) {
            return if (isFirstChild) {
                // always wrap the first element
                Wrap.createWrap(WrapType.ALWAYS, true)
            } else {
                Wrap.createWrap(WrapType.NORMAL, false)
            }
        }
        if (childNode.elementType == OnjTypes.OBJECT_ENTRY) {
            return Wrap.createWrap(WrapType.ALWAYS, true)
        }
        if (childNode.elementType == OnjTypes.R_BRACKET || childNode.elementType == OnjTypes.R_BRACE) {
            return Wrap.createWrap(WrapType.ALWAYS, true)
        }
        return Wrap.createWrap(WrapType.NONE, false)
    }

    override fun getChildIndent(): Indent? {
        // TODO: fix this
        if (node.elementType.debugName == "FILE") {
            // When the file is incomplete the user probably just typed an opening bracket/brace to start an object/array
            if (!isIncomplete) {
                return Indent.getNoneIndent()
            }
        }

        return Indent.getNormalIndent()
    }

}
