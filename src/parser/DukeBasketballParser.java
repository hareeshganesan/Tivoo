package parser;

import java.util.*;

import org.w3c.dom.*;

import event.*;

public class DukeBasketballParser extends Parser {



	@Override
	public void parse(ArrayList<Event> myEvents, Document myDocument) {
		String xPath = "/dataroot/Calendar";
		NodeList eventList = getTagNodes(myDocument, xPath);

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

}