package parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.*;
import event.*;


public class DukeBasketballParser extends Parser
{



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
        return reformatDateString(info);
    }


    protected String getEndDate (Node currentEvent)
    {
        String endDate = getTagValue(currentEvent, "EndDate/text()");
        String endTime = getTagValue(currentEvent, "EndTime/text()");
        String info = endDate + " " + endTime;
        return reformatDateString(info);
    }


    private String reformatDateString (String info)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Date date = new Date();
        DateFormat eventFormat = new SimpleDateFormat(Event.dateFormat);

        try
        {
            date = df.parse(info);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return eventFormat.format(date);
    }


//    @Override
//    protected HashMap<String, String> getMyFields (Node currentEvent)
//    {
//        HashMap<String, String> fields = new HashMap<String, String>();
//        fields.put("title", getTitle(currentEvent));
//        fields.put("summary", getSummary(currentEvent));
//        fields.put("url", getURL(currentEvent));
//        fields.put("startTime", getStartDate(currentEvent));
//        fields.put("endTime", getEndDate(currentEvent));
//        return fields;
//    }


}
