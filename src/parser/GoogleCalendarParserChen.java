package parser;

import org.w3c.dom.Node;

public class GoogleCalendarParserChen extends Parser{

	@Override
	protected String getHead() {
		// TODO Auto-generated method stub
		return "/feed/entry";
	}
	protected String getTitle (Node currentEvent)
    {
        return getTagValue(currentEvent, "title/text()");
    }
	protected String getURL (Node currentEvent)
    {
        return getTagValue(currentEvent, "link[1]/attribute::href");
    }
	protected String getStartDate (Node currentEvent)
    {
        return getTagValue(currentEvent, "summary/text()");
    }
}