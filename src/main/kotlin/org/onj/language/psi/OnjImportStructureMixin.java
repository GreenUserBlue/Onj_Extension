package org.onj.language.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.onj.language.OnjVariableSymbolDeclaration;

public class OnjImportStructureMixin extends ASTWrapperPsiElement {

    private OnjVariableSymbolDeclaration symbolDeclaration = null;

    public OnjImportStructureMixin(@NotNull ASTNode node) {
        super(node);
    }

    public OnjVariableSymbolDeclaration getSymbolDeclaration() {
        if (symbolDeclaration == null) {
            symbolDeclaration = new OnjVariableSymbolDeclaration(this);
        }
        return symbolDeclaration;
    }

}
