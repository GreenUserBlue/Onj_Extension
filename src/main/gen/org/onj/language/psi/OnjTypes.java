// This is a generated file. Not intended for manual editing.
package org.onj.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.onj.language.psi.impl.*;

public interface OnjTypes {

  IElementType BOOL = new OnjElementType("BOOL");
  IElementType DIGIT = new OnjElementType("DIGIT");
  IElementType ELEM = new OnjElementType("ELEM");
  IElementType ELEM_NO_PAIR = new OnjElementType("ELEM_NO_PAIR");
  IElementType FLOAT = new OnjElementType("FLOAT");
  IElementType INTEGER = new OnjElementType("INTEGER");
  IElementType PAIR = new OnjElementType("PAIR");
  IElementType PRIMITIVE = new OnjElementType("PRIMITIVE");

  IElementType ASSIGN = new OnjTokenType("ASSIGN");
  IElementType NAME_CHARACTER = new OnjTokenType("NAME_CHARACTER");
  IElementType SEPARATOR = new OnjTokenType("SEPARATOR");
  IElementType STRING_VALUE = new OnjTokenType("STRING_VALUE");
  IElementType WHITE_SPACE = new OnjTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BOOL) {
        return new OnjBoolImpl(node);
      }
      else if (type == DIGIT) {
        return new OnjDigitImpl(node);
      }
      else if (type == ELEM) {
        return new OnjElemImpl(node);
      }
      else if (type == ELEM_NO_PAIR) {
        return new OnjElemNoPairImpl(node);
      }
      else if (type == FLOAT) {
        return new OnjFloatImpl(node);
      }
      else if (type == INTEGER) {
        return new OnjIntegerImpl(node);
      }
      else if (type == PAIR) {
        return new OnjPairImpl(node);
      }
      else if (type == PRIMITIVE) {
        return new OnjPrimitiveImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
