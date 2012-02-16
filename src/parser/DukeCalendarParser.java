package parser;

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
        String startDate = getTagValue(currentEvent, "start/longdate/text()") + " ";
        String startTime = getTagValue(currentEvent, "start/utcdate/text()").substring(9, 11) + ":" +
                           getTagValue(currentEvent, "start/utcdate/text()").substring(11, 13)+" UTC";
        return getDateFromString(startDate + startTime);
    }

    @Override
    protected Date getEndDate(Node currentEvent) {
        String endDate = getTagValue(currentEvent, "end/longdate/text()") + " ";
        String endTime = getTagValue(currentEvent, "end/utcdate/text()").substring(9, 11) + ":" + 
                         getTagValue(currentEvent, "end/utcdate/text()").substring(11, 13) +" UTC";
        return getDateFromString(endDate + endTime);
    }

}