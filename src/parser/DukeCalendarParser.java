package parser;

import java.util.*;

import org.w3c.dom.*;

import event.*;

public class DukeCalendarParser extends Parser {


	@Override
	public void parse(ArrayList<Event> myEvents, Document myDocument) {
		String xPath = "/events/event";
		NodeList eventList = getTagNodes(myDocument, xPath);

	
		for (int temp = 0; temp < eventList.getLength(); temp++) {
			Node currentEvent = eventList.item(temp);
			String title = getTagValue(currentEvent, "summary/text()");
			String summary = getTagValue(currentEvent, "description/text()");
			String url = getTagValue(currentEvent, "link/text()");

			// Any way to simplify the following?
			String startDate = getTagValue(currentEvent,
					"start/longdate/text()") + " ";
			String startTime = getTagValue(currentEvent, "start/utcdate/text()")
					.substring(9, 11)
					+ ":"
					+ getTagValue(currentEvent, "start/utcdate/text()")
							.substring(11, 13)+" UTC";
			Date start = getDateFromString(startDate + startTime);

			String endDate = getTagValue(currentEvent, "end/longdate/text()")
					+ " ";
			String endTime = getTagValue(currentEvent, "end/utcdate/text()")
					.substring(9, 11)
					+ ":"
					+ getTagValue(currentEvent, "end/utcdate/text()")
							.substring(11, 13)+" UTC";
			Date end = getDateFromString(endDate + endTime);
			// System.out.format("%s %d %d %d %d %d\n", title, start.getMonth(),
			// start.getDate(), start.getYear(), start.getHours(),
			// start.getMinutes());
			// System.out.format("%d %d %d %d %d\n", end.getMonth(),
			// end.getDate(), end.getYear(), end.getHours(), end.getMinutes());

			myEvents.add(new Event(title, summary, start, end, url));
		}
	}

}