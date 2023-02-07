package org.onj.language.highlighting

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.vfs.VirtualFile
import org.onj.language.highlighting.OnjSyntaxHighlighter


class OnjSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(
        project: com.intellij.openapi.project.Project?,
        virtualFile: VirtualFile?
    ): SyntaxHighlighter {
        return OnjSyntaxHighlighter()
    }
}