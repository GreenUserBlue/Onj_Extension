@file:Suppress("UnstableApiUsage")

package org.onj.language

import com.intellij.model.Pointer
import com.intellij.model.Symbol
import com.intellij.model.psi.PsiSymbolDeclaration
import com.intellij.navigation.NavigatableSymbol
import com.intellij.navigation.NavigationTarget
import com.intellij.navigation.TargetPresentation
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
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

    override fun createPointer(): Pointer<out Symbol> = this

    override fun dereference(): OnjVariableSymbol = this

    override fun getNavigationTargets(project: Project): MutableCollection<out NavigationTarget> = mutableListOf(this)

    override fun isValid(): Boolean = true

    override fun getNavigatable(): Navigatable = declaringPsiElement

    override fun getTargetPresentation(): TargetPresentation {
        TODO("Not yet implemented")
    }
}