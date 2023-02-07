package org.onj.language.utils

import com.intellij.lang.ASTNode

object Utils {

    inline fun iterateOverAstChildren(astNode: ASTNode, block: (child: ASTNode) -> Unit) {
        var curChild = astNode.firstChildNode
        while (curChild != null) {
            block(curChild)
            curChild = curChild.treeNext
        }
    }

}
