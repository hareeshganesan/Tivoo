package parser;

import java.util.PropertyResourceBundle;
import org.w3c.dom.Node;


public class TVParser extends Parser
{
    private final String myOldFormat =
        PropertyResourceBundle.getBundle("myProperties").getString("tvFormat");


    @Override
    protected String getHead ()
    {
        return "/tv/programme";
    }


    protected String getTitle (Node currentEvent)
    {
        return getTagValue(currentEvent, "title/text()");
    }


    protected String getSummary (Node currentEvent)
    {
        return getTagValue(currentEvent, "desc/text()");
    }


    protected String getURL (Node currentEvent)
    {
        return getTagValue(currentEvent, "attribute::channel");
    }


    protected String getStartDate (Node currentEvent)
    {
        String result = getTagValue(currentEvent, "attribute::start");
        return reformatDateString(result, myOldFormat);
    }


    protected String getEndDate (Node currentEvent)
    {
        String result = getTagValue(currentEvent, "attribute::stop");
        return reformatDateString(result, myOldFormat);
    }

}
