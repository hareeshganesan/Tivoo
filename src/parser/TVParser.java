package parser;

import java.util.ArrayList;
import org.w3c.dom.Node;

public class TVParser extends Parser
{

    @Override
    protected String getHead ()
    {
      return "/tv/programme";
    }
    
    protected String getTitle(Node currentEvent) {
        return getTagValue(currentEvent, "title/text()");
    }

    protected String getSummary(Node currentEvent) {
        return getTagValue(currentEvent, "desc/text()");
    }

    protected String getURL(Node currentEvent) {
        return getTagValue(currentEvent, "getAttribute(channel)");
    }

    protected String getStartDate(Node currentEvent) {
       return null;
    }

    protected String getEndDate(Node currentEvent) {
        return null;
    }

}