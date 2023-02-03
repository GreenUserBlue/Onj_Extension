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

public class OnjPrimitiveImpl extends ASTWrapperPsiElement implements OnjPrimitive {

  public OnjPrimitiveImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull OnjVisitor visitor) {
    visitor.visitPrimitive(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OnjVisitor) accept((OnjVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public OnjBool getBool() {
    return findChildByClass(OnjBool.class);
  }

  @Override
  @Nullable
  public OnjFloat getFloat() {
    return findChildByClass(OnjFloat.class);
  }

  @Override
  @Nullable
  public OnjInteger getInteger() {
    return findChildByClass(OnjInteger.class);
  }

}
