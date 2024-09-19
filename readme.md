# ONJ Extension

## General
This is a Plugin to add support for the [onj](https://github.com/blueUserRed/Onj) language.

Features:
<ul>
<li>syntax highlighting</li>
<li>basic syntax error checking</li>
<li>auto formatter</li>
<li>simple 'go to definition' logic</li>
</ul>

Limitations: 
<ul>
<li>error checking beyond the syntax level (e.g. duplicate keys)</li>
<li>no refactoring operations like rename refactoring</li>
<li>no support for schemas</li>
<li>no quick fixes</li>
</ul>


## Project Setup

- Go to [Onj.bnf](src/main/kotlin/org/onj/language/Onj.bnf)
- Press Strg+Shift+G or right click the File and execute "Generate Parser Code"
<br><br>
- Go to [Onj.bnf](src/main/kotlin/org/onj/language/onj.flex)
- Press Strg+Shift+G or right click the File and execute "Run JFlex Generator"
  - maybe you need to install jflex first, just select a the project folder