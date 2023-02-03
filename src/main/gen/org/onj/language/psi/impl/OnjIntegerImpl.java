// This is a generated file. Not intended for manual editing.
package org.onj.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.onj.language.psi.OnjTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.onj.language.psi.*;

public class OnjIntegerImpl extends ASTWrapperPsiElement implements OnjInteger {

  public OnjIntegerImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull OnjVisitor visitor) {
    visitor.visitInteger(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OnjVisitor) accept((OnjVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public OnjDigit getDigit() {
    return findNotNullChildByClass(OnjDigit.class);
  }

  @Override
  @Nullable
  public OnjInteger getInteger() {
    return findChildByClass(OnjInteger.class);
  }

}
