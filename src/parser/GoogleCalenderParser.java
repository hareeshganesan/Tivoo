package parser;

import java.util.ArrayList;
import org.w3c.dom.Node;

public class GoogleCalenderParser extends Parser
{

    @Override
    protected String getHead ()
    {
      return "/feed/entry";
    }
    
    protected String getTitle(Node currentEvent) {
        return getTagValue(currentEvent, "title/text()");
    }

    protected String getSummary(Node currentEvent) {
        return getTagValue(currentEvent, "content/text()");
    }

    protected String getURL(Node currentEvent) {
        return getTagValue(currentEvent, "id/text()");
    }

    protected String getStartDate(Node currentEvent) {
       return null;
    }

    protected String getEndDate(Node currentEvent) {
        return null;
    }

}
