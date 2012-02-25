package parser;

import org.w3c.dom.Node;

public class NFLParser extends Parser {

	@Override
	protected String getHead() {
		// TODO Auto-generated method stub
		return "/document/row";
	}

	protected String getTitle(Node currentEvent) {
		return getTagValue(currentEvent, "Col1/text()");
	}

	protected String getSummary(Node currentEvent) {
		return "Location: " + getTagValue(currentEvent, "Col15/text()");
	}

	protected String getURL(Node currentEvent) {
		return getTagValue(currentEvent, "Col2/text()");
	}

	protected String getStartDate(Node currentEvent) {

		return getTagValue(currentEvent, "Col8/text()");
	}

	protected String getEndDate(Node currentEvent) {

		return getTagValue(currentEvent, "Col9/text()");
	}
    public static ParserFactory getFactory() {
        return new ParserFactory(new NFLParser());
    }

    @Override
    protected Parser newParser() {
        return new NFLParser();
    }

}