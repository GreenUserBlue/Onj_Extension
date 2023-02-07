package org.onj.language.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import org.onj.language.OnjParserDefinition
import org.onj.language.psi.OnjTokenSets
import org.onj.language.psi.OnjTypes
import kotlin.reflect.jvm.jvmName

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
        var curChild = node.firstChildNode
        val blocks = mutableListOf<Block>()
        while (curChild != null) {
            if (curChild.elementType == TokenType.WHITE_SPACE) {
                curChild = curChild.treeNext
                continue
            }
            val block = OnjBlock(
                spacingBuilder,
                curChild,
                Wrap.createWrap(WrapType.NONE, false),
                null,
                getChildIndent(curChild)
            )
            blocks.add(block)
            curChild = curChild.treeNext
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

        return Indent.getNoneIndent()
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
