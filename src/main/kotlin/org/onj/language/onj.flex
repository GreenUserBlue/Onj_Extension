// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.onj.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.onj.language.psi.OnjTypes;
import com.intellij.psi.TokenType;

%%
%public
%class OnjLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]+?
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
//VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("//")[^\r\n]*?
BLOCK_COMMENT=("/*").*?("*/")
SEPARATOR=[,]
SEMIQOLON=[;]
ASSIGN=[:]
DOT=[*/]
DASH=[+-]
HASHTAG=[#]
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
NAME_CHARACTER=\p{L}\w*
STRING_VALUE=[\"]([^\"\\]|\\.)*[\"]|'([^'\\]|\\.)*'

%state WAITING_VALUE

%%

//<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return OnjTypes.COMMENT; }
//<YYINITIAL> {BLOCK_COMMENT}                                 { yybegin(YYINITIAL); return OnjTypes.BLOCK_COMMENT; }

//<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return OnjTypes.NAMED_KEYS; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return OnjTypes.SEPARATOR; }
<YYINITIAL> {SEMIQOLON}                                     { yybegin(WAITING_VALUE); return OnjTypes.SEMIQOLON; }
<YYINITIAL> {ASSIGN}                                     { yybegin(WAITING_VALUE); return OnjTypes.ASSIGN; }
<YYINITIAL> {DOT}                                     { yybegin(WAITING_VALUE); return OnjTypes.DOT; }
<YYINITIAL> {DASH}                                     { yybegin(WAITING_VALUE); return OnjTypes.DASH; }
<YYINITIAL> {STRING_VALUE}                                     { yybegin(WAITING_VALUE); return OnjTypes.STRING_VALUE; }
<YYINITIAL> {NAME_CHARACTER}                                { yybegin(YYINITIAL); return OnjTypes.NAME_CHARACTER; }
<YYINITIAL> {HASHTAG}                                     { yybegin(WAITING_VALUE); return OnjTypes.HASHTAG; }


<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {SEPARATOR}                     { yybegin(WAITING_VALUE); return OnjTypes.SEPARATOR; }
<WAITING_VALUE> {SEMIQOLON}                     { yybegin(WAITING_VALUE); return OnjTypes.SEMIQOLON; }
<WAITING_VALUE> {ASSIGN}                     { yybegin(WAITING_VALUE); return OnjTypes.ASSIGN; }
<WAITING_VALUE> {DOT}                     { yybegin(WAITING_VALUE); return OnjTypes.DOT; }
<WAITING_VALUE> {DASH}                     { yybegin(WAITING_VALUE); return OnjTypes.DASH; }
<WAITING_VALUE> {STRING_VALUE}                     { yybegin(WAITING_VALUE); return OnjTypes.STRING_VALUE; }
<WAITING_VALUE> {NAME_CHARACTER}                              { yybegin(WAITING_VALUE); return OnjTypes.NAME_CHARACTER; }
<WAITING_VALUE> {HASHTAG}                              { yybegin(WAITING_VALUE); return OnjTypes.HASHTAG; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }