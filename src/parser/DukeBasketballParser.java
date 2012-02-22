package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return getDateFromString(startDate + " " + startTime, dateFormat);
    }

    @Override
    protected Date getEndDate(Node currentEvent) {
        String endDate = getTagValue(currentEvent, "EndDate/text()");
        String endTime = getTagValue(currentEvent, "EndTime/text()");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return getDateFromString(endDate + " " + endTime, dateFormat);
    }
    
    public static ParserFactory getFactory() {
        return new ParserFactory(new DukeBasketballParser());
    }

    @Override
    public Parser newParser() {
        return new DukeBasketballParser();
    }

}