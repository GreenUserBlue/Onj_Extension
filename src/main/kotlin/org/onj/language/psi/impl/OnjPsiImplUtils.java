package org.onj.language.psi.impl;

import com.intellij.model.psi.PsiSymbolReference;
import org.jetbrains.annotations.NotNull;
import org.onj.language.OnjVariableSymbolReference;
import org.onj.language.psi.OnjVariableExpression;

import java.util.ArrayList;
import java.util.Collection;

public class OnjPsiImplUtils {

    public static @NotNull Collection<? extends @NotNull PsiSymbolReference> getOwnReferences(OnjVariableExpression element) {
        ArrayList<PsiSymbolReference> toRet = new ArrayList<>();
        toRet.add(new OnjVariableSymbolReference(element));
        return toRet;
    }

}
