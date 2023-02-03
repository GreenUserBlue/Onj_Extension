package org.onj.language

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.vfs.VirtualFile
import org.apache.tools.ant.Project


class OnjSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(
        project: com.intellij.openapi.project.Project?,
        virtualFile: VirtualFile?
    ): SyntaxHighlighter {
        return OnjSyntaxHighlighter()
    }
}