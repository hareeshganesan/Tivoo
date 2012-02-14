package parser;

import java.io.File;
import java.util.*;
import org.w3c.dom.*;
import event.*;

public class DukeCalendarParser extends Parser {
	private ArrayList<Event> myEvents;
	private Document myDocument;

	public DukeCalendarParser(File file) {
	    myEvents = new ArrayList<Event>();
	    myDocument = generateDocument(file);
	}
	
    @Override
	public void parse() {
		    String xPath = "/events/event";
			NodeList eventList = getTagNodes(myDocument, xPath);

			myEvents = new ArrayList<Event>();
			for (int temp = 0; temp < eventList.getLength(); temp++) {
				Node currentEvent = eventList.item(temp);
				String title = getTagValue(currentEvent, "summary/text()");
				String summary = getTagValue(currentEvent, "description/text()");
				String url = getTagValue(currentEvent, "link/text()");
				
				// Any way to simplify the following?
		        String startTimeForParse1 = getTagValue(currentEvent, "start/longdate/text()") + " ";
		        String startTimeForParse2 = getTagValue(currentEvent, "start/utcdate/text()").substring(9, 11) + ":" +
		                getTagValue(currentEvent, "start/utcdate/text()").substring(11, 13);
		        Date startTime = getDateFromString(startTimeForParse1 + startTimeForParse2);
		        
		        String endTimeForParse1 = getTagValue(currentEvent, "end/longdate/text()") + " ";
		        String endTimeForParse2 = getTagValue(currentEvent, "end/utcdate/text()").substring(9, 11)+":"+
		                getTagValue(currentEvent, "end/utcdate/text()").substring(11, 13);
                Date endTime = getDateFromString(endTimeForParse1 + endTimeForParse2);

				myEvents.add(new Event(title, summary, startTime, endTime, url));
			}
	}
	
	@Override
    public List<Event> getEventList() {
        return new ArrayList<Event>(myEvents);
    }

}