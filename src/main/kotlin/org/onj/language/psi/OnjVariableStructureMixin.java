package org.onj.language.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.onj.language.symbols.OnjVariableSymbolDeclaration;

public class OnjVariableStructureMixin extends ASTWrapperPsiElement {

    private OnjVariableSymbolDeclaration symbolDeclaration = null;

    public OnjVariableStructureMixin(@NotNull ASTNode node) {
        super(node);
    }

    public OnjVariableSymbolDeclaration getSymbolDeclaration() {
        if (symbolDeclaration == null) {
            symbolDeclaration = new OnjVariableSymbolDeclaration(this);
        }
        return symbolDeclaration;
    }

}
