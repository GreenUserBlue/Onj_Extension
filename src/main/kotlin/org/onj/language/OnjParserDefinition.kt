package org.onj.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.lexer.OnjLexerAdapter
import org.onj.language.psi.OnjFile
import org.onj.language.psi.OnjTokenSets
import org.onj.language.psi.OnjTypes

class OnjParserDefinition : ParserDefinition {

    private val FILE = IFileElementType(OnjLanguage)

    override fun createLexer(project: Project?): Lexer {
        return OnjLexerAdapter()
    }

    override fun createParser(project: Project?): PsiParser {
        return OnjParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE;
    }

    override fun getCommentTokens(): TokenSet {
        return OnjTokenSets.COMMENTS;
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY;
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return OnjTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return OnjFile(viewProvider)
    }
}