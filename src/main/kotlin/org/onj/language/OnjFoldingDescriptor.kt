package org.onj.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import org.onj.language.psi.OnjTypes
import org.onj.language.utils.Utils.iterateOverAstChildren

class OnjFoldingDescriptor : FoldingBuilder, DumbAware {

    override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
        val foldRegions = mutableListOf<FoldingDescriptor>()
        buildFoldRegions(node, foldRegions)
        return foldRegions.toTypedArray()
    }

    private fun buildFoldRegions(node: ASTNode, regions: MutableList<FoldingDescriptor>) {
        if (node.elementType == OnjTypes.OBJECT || node.elementType == OnjTypes.ARRAY) {
            regions.add(FoldingDescriptor(node, node.textRange))
        }
        iterateOverAstChildren(node) { buildFoldRegions(it, regions) }
    }

    override fun getPlaceholderText(node: ASTNode): String =
        if (node.elementType == OnjTypes.OBJECT) {
            "{ ... }"
        } else {
            "[ ... ]"
        }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}