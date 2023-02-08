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
            .around(OnjTypes.BINARY_OPERATOR).spaceIf(true)
            .afterInside(OnjTypes.MINUS, OnjTypes.UNARY_EXPRESSION).spaceIf(false)
            .after(OnjTypes.COLON).spaceIf(true)
            .before(OnjTypes.COLON).spaceIf(false)
            .aroundInside(OnjTypes.DOT, OnjTypes.TRIPLE_DOT).spaceIf(false)
            .after(OnjTypes.DOLLAR).spaceIf(false)
            .after(OnjTypes.NAMED_OBJECT_NAME).spaceIf(true)
            .aroundInside(OnjTypes.FUNCTION_NAME, OnjTypes.INFIX_FUNCTION_CALL_EXPRESSION).spaceIf(true)
            .afterInside(OnjTypes.FUNCTION_NAME, OnjTypes.FUNCTION_CALL_EXPRESSION).spaceIf(false)
            .around(OnjTypes.HASH).spaceIf(false)
            .after(OnjTypes.L_PAREN).spaceIf(false)
            .before(OnjTypes.R_PAREN).spaceIf(false)
            .after(OnjTypes.IMPORT).spaceIf(true)
            .before(OnjTypes.AS_CONTEXT_DEPENDENT_KEYWORD).spaceIf(true)
            .before(OnjTypes.SEMICOLON).spaceIf(false)
            .around(OnjTypes.EQUALS).spaceIf(true)
            .before(OnjTypes.COMMA).spaceIf(false)
            .after(OnjTypes.COMMA).spaceIf(true)
        return spacingBuilder
    }


}
