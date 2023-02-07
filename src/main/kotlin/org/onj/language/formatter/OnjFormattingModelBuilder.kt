package org.onj.language.formatter

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import org.onj.language.OnjLanguage
import org.onj.language.psi.OnjTokenSets
import org.onj.language.psi.OnjTypes

class OnjFormattingModelBuilder : FormattingModelBuilder {

    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings

        val spacing = createSpacingBuilder(codeStyleSettings)

        return FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            OnjBlock(
                spacing,
                formattingContext.node,
                Wrap.createWrap(WrapType.NONE, false),
                null,
                Indent.getNoneIndent()
            ),
            codeStyleSettings
        )
    }

    private fun createSpacingBuilder(codeStyleSettings: CodeStyleSettings): SpacingBuilder {
        val spacingBuilder = SpacingBuilder(codeStyleSettings, OnjLanguage)
        spacingBuilder
            .around(OnjTokenSets.binaryOperators)
            .spaces(1)
        return spacingBuilder
    }


}
