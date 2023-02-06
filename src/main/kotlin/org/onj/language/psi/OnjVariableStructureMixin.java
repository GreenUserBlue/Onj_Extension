package org.onj.language.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.NavigatablePsiElement;
import org.jetbrains.annotations.NotNull;
import org.onj.language.OnjVariableSymbolDeclaration;
import org.onj.language.psi.impl.OnjVariableStructureImpl;

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
