package org.onj.language.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

//if a method is defined within an interface, it will be ignored for some reason
interface OnjNamedElement : PsiNameIdentifierOwner

interface OnjDocumentableElement : PsiElement