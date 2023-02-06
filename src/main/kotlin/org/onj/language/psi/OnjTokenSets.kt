package org.onj.language.psi

import com.intellij.psi.tree.TokenSet

interface OnjTokenSets {
    companion object {
//        val NAMES: TokenSet = TokenSet.create(OnjTypes.)
        val COMMENTS: TokenSet = TokenSet.create(OnjTypes.LINE_COMMENT, OnjTypes.BLOCK_COMMENT)
//        val KEYS: TokenSet = TokenSet.create(OnjTypes.PRIMITIVE)
//        val VALUES: TokenSet = TokenSet.create(OnjTypes.ELEM)
    }
}