// This is a generated file. Not intended for manual editing.
package org.onj.language;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.onj.language.psi.OnjTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class OnjParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return OnjFile(b, l + 1);
  }

  /* ********************************************************** */
  // item_ | WHITE_SPACE?
  static boolean OnjFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OnjFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_(b, l + 1);
    if (!r) r = OnjFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE?
  private static boolean OnjFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OnjFile_1")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // "true"|"false"
  public static boolean bool(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bool")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOL, "<bool>");
    r = consumeToken(b, "true");
    if (!r) r = consumeToken(b, "false");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"
  public static boolean digit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "digit")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIGIT, "<digit>");
    r = consumeToken(b, "0");
    if (!r) r = consumeToken(b, "1");
    if (!r) r = consumeToken(b, "2");
    if (!r) r = consumeToken(b, "3");
    if (!r) r = consumeToken(b, "4");
    if (!r) r = consumeToken(b, "5");
    if (!r) r = consumeToken(b, "6");
    if (!r) r = consumeToken(b, "7");
    if (!r) r = consumeToken(b, "8");
    if (!r) r = consumeToken(b, "9");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( primitive | STRING_VALUE ) WHITE_SPACE?
  public static boolean elem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEM, "<elem>");
    r = elem_0(b, l + 1);
    r = r && elem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // primitive | STRING_VALUE
  private static boolean elem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elem_0")) return false;
    boolean r;
    r = primitive(b, l + 1);
    if (!r) r = consumeToken(b, STRING_VALUE);
    return r;
  }

  // WHITE_SPACE?
  private static boolean elem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elem_1")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  /* ********************************************************** */
  // (integer "." integer) | ("." integer)
  public static boolean float_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_$")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLOAT, "<float $>");
    r = float_0(b, l + 1);
    if (!r) r = float_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // integer "." integer
  private static boolean float_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integer(b, l + 1);
    r = r && consumeToken(b, ".");
    r = r && integer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "." integer
  private static boolean float_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "float_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ".");
    r = r && integer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // digit integer|(digit)
  public static boolean integer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTEGER, "<integer>");
    r = integer_0(b, l + 1);
    if (!r) r = integer_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // digit integer
  private static boolean integer_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = digit(b, l + 1);
    r = r && integer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (digit)
  private static boolean integer_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = digit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WHITE_SPACE? elem WHITE_SPACE? (((SEPARATOR item_))|(SEPARATOR?))
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item__0(b, l + 1);
    r = r && elem(b, l + 1);
    r = r && item__2(b, l + 1);
    r = r && item__3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE?
  private static boolean item__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__0")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  // WHITE_SPACE?
  private static boolean item__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__2")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  // ((SEPARATOR item_))|(SEPARATOR?)
  private static boolean item__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item__3_0(b, l + 1);
    if (!r) r = item__3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR item_
  private static boolean item__3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && item_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR?
  private static boolean item__3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__3_1")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  /* ********************************************************** */
  // WHITE_SPACE? (bool | float | integer | "null")
  public static boolean primitive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitive")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMITIVE, "<primitive>");
    r = primitive_0(b, l + 1);
    r = r && primitive_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE?
  private static boolean primitive_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitive_0")) return false;
    consumeToken(b, WHITE_SPACE);
    return true;
  }

  // bool | float | integer | "null"
  private static boolean primitive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitive_1")) return false;
    boolean r;
    r = bool(b, l + 1);
    if (!r) r = float_$(b, l + 1);
    if (!r) r = integer(b, l + 1);
    if (!r) r = consumeToken(b, "null");
    return r;
  }

}
