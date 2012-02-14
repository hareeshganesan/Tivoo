package parser;

import java.io.File;
import java.util.*;
import org.w3c.dom.*;
import event.*;

public class DukeBasketballParser extends Parser {
    private ArrayList<Event> myEvents;
    private Document myDocument;

    public DukeBasketballParser(File file) {
        myEvents = new ArrayList<Event>();
        myDocument = generateDocument(file);
    }

    @Override
    public void parse() {
            String xPath = "/dataroot/Calendar";
            NodeList eventList = getTagNodes(myDocument, xPath);

            myEvents = new ArrayList<Event>();
            for (int temp = 0; temp < eventList.getLength(); temp++) {
                Node currentEvent = eventList.item(temp);
                String title = getTagValue(currentEvent, "Subject/text()");
                String summary = getTagValue(currentEvent, "Description/text()");
                int index = summary.indexOf("http://");
                String url = summary.substring(index);
                
                String startDate = getTagValue(currentEvent, "StartDate/text()");
                String startTime = getTagValue(currentEvent, "StartTime/text()");
                Date start = getDateFromString(startDate + " " + startTime);
                
                String endDate = getTagValue(currentEvent, "EndDate/text()");
                String endTime = getTagValue(currentEvent, "EndTime/text()");
                Date end = getDateFromString(endDate + " " + endTime);
                
                myEvents.add(new Event(title, summary, start, end, url));
            }
    }

    @Override
    public List<Event> getEventList() {
        return new ArrayList<Event>(myEvents);
    }

}