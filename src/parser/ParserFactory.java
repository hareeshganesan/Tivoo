package parser;

public class ParserFactory {
    Parser myParser;
    
    public ParserFactory(Parser parser) {
        myParser = parser;
    }
    
    public Parser newParser() {
        return myParser.newParser();
    }
}