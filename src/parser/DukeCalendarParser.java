package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.*;
import event.*;

public class DukeCalendarParser extends Parser {

    public DukeCalendarParser() {
        myDocument = null;
        myEvents = new ArrayList<Event>();
    }
	
	@Override
    protected String getHead() {
        return "/events/event";
    }

    @Override
    protected String getTitle(Node currentEvent) {
        return getTagValue(currentEvent, "summary/text()");
    }

    @Override
    protected String getSummary(Node currentEvent) {
        return getTagValue(currentEvent, "description/text()");
    }

    @Override
    protected String getURL(Node currentEvent) {
        return getTagValue(currentEvent, "link/text()");
    }

    @Override
    protected Date getStartDate(Node currentEvent) {
        String startDate = getTagValue(currentEvent, "start/shortdate/text()");
        String startTime = getTagValue(currentEvent, "start/time/text()"); 
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        return getDateFromString(startDate + " " + startTime, dateFormat);
    }

    @Override
    protected Date getEndDate(Node currentEvent) {
        String endDate = getTagValue(currentEvent, "end/shortdate/text()");
        String endTime = getTagValue(currentEvent, "end/time/text()"); 
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        return getDateFromString(endDate + " " + endTime, dateFormat);
    }

}