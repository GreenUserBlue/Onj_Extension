package org.lexer

import com.intellij.lexer.FlexAdapter
import org.onj.language.OnjLexer;

class OnjLexerAdapter:FlexAdapter(OnjLexer(null)) {

}