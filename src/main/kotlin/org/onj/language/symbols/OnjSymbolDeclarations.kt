@file:Suppress("UnstableApiUsage")

package org.onj.language.symbols

import com.intellij.model.Pointer
import com.intellij.model.Symbol
import com.intellij.model.psi.PsiSymbolDeclaration
import com.intellij.navigation.NavigatableSymbol
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.platform.backend.navigation.NavigationRequest
import com.intellij.platform.backend.navigation.NavigationTarget
import com.intellij.platform.backend.presentation.TargetPresentation
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement

class OnjVariableSymbolDeclaration(
    private val declaringPsiElement: NavigatablePsiElement
) : PsiSymbolDeclaration {

    private val symbol = OnjVariableSymbol(declaringPsiElement)

    override fun getDeclaringElement(): PsiElement = declaringPsiElement

    override fun getRangeInDeclaringElement(): TextRange = declaringPsiElement.textRangeInParent

    override fun getSymbol(): Symbol = symbol
}

class OnjVariableSymbol(
    private val declaringPsiElement: NavigatablePsiElement
) : Pointer<OnjVariableSymbol>, NavigatableSymbol, NavigationTarget {

    override fun createPointer(): OnjVariableSymbol = this

    override fun dereference(): OnjVariableSymbol = this

    override fun getNavigationTargets(project: Project): MutableCollection<out NavigationTarget> = mutableListOf(this)

    override fun navigationRequest(): NavigationRequest? =declaringPsiElement.navigationRequest()

    override fun computePresentation(): TargetPresentation {
        TODO("Not yet implemented")
    }
}