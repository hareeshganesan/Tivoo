package parser;

import java.util.PropertyResourceBundle;
import org.w3c.dom.Node;


public class DukeBasketballParser extends Parser
{
    private final String myOldFormat =
        PropertyResourceBundle.getBundle("myProperties")
                              .getString("dukeballFormat");


    @Override
    protected String getHead ()
    {
        return "/dataroot/Calendar";
    }


    protected String getTitle (Node currentEvent)
    {
        return getTagValue(currentEvent, "Subject/text()");
    }


    protected String getSummary (Node currentEvent)
    {
        return getTagValue(currentEvent, "Description/text()");
    }


    protected String getURL (Node currentEvent)
    {
        String summary = getSummary(currentEvent);
        int index = summary.indexOf("http://");
        return summary.substring(index);
    }


    protected String getStartDate (Node currentEvent)
    {
        String startDate = getTagValue(currentEvent, "StartDate/text()");
        String startTime = getTagValue(currentEvent, "StartTime/text()");
        String info = startDate + " " + startTime;
        return reformatDateString(info, myOldFormat);
    }


    protected String getEndDate (Node currentEvent)
    {
        String endDate = getTagValue(currentEvent, "EndDate/text()");
        String endTime = getTagValue(currentEvent, "EndTime/text()");
        String info = endDate + " " + endTime;
        return reformatDateString(info, myOldFormat);
    }

}
