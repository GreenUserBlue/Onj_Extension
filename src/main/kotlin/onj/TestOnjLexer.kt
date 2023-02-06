package onj

import onj.parser.OnjToken
import onj.parser.Tokenizer
fun main(){
    println("works")
}
public class TestOnjLexer {
    lateinit var tokens: List<OnjToken>
    lateinit var buffer: CharSequence
    var startOffset: Int = 0
    var endOffset: Int = 0
    var curPos: Int = 0

     fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        tokens = Tokenizer(buffer.toString(), "noName", false).tokenize()
        tokens.forEach(){
            println(it)
        }
    }

     fun getState(): Int {
        TODO("Not yet implemented")
    }

//     fun getTokenType(): IElementType? {
//        TODO("Not yet implemented")
//    }

     fun getTokenStart(): Int {
        TODO("Not yet implemented")
    }

     fun getTokenEnd(): Int {
        TODO("Not yet implemented")
    }

     fun advance() {
        TODO("Not yet implemented")
    }

//     fun getCurrentPosition(): LexerPosition {
//        TODO("Not yet implemented")
//    }
//
//     fun restore(position: LexerPosition) {
//        TODO("Not yet implemented")
//    }

     fun getBufferSequence(): CharSequence {
        return buffer
    }

     fun getBufferEnd(): Int {
        return endOffset
    }

}