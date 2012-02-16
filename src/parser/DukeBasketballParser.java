package parser;

import java.util.*;

import org.w3c.dom.*;

import event.*;

public class DukeBasketballParser extends Parser {
    
    public DukeBasketballParser() {
        myDocument = null;
        myEvents = new ArrayList<Event>();
    }
    
    @Override
    protected String getHead() {
        return "/dataroot/Calendar";
    }
    
    @Override
    protected String getTitle(Node currentEvent) {
        return getTagValue(currentEvent, "Subject/text()");
    }

    @Override
    protected String getSummary(Node currentEvent) {
        return getTagValue(currentEvent, "Description/text()");
    }

    @Override
    protected String getURL(Node currentEvent) {
        String summary = getSummary(currentEvent);
        int index = summary.indexOf("http://");
        return summary.substring(index);
    }

    @Override
    protected Date getStartDate(Node currentEvent) {
        String startDate = getTagValue(currentEvent, "StartDate/text()");
        String startTime = getTagValue(currentEvent, "StartTime/text()");
        return getDateFromString(startDate + " " + startTime);
    }

    @Override
    protected Date getEndDate(Node currentEvent) {
        String endDate = getTagValue(currentEvent, "EndDate/text()");
        String endTime = getTagValue(currentEvent, "EndTime/text()");
        return getDateFromString(endDate + " " + endTime);
    }

}