package parser;

import java.util.*;

import org.w3c.dom.*;

import event.*;

public class DukeCalendarParser extends Parser {
	private String[] myTime = { "year", "twodigitmonth", "twodigitday",
			"twodigithour", "twodigitminute" };

	private String format(ArrayList<String> list) {
		return list.get(0) + "-" + list.get(1) + "-" + list.get(2) + " "
				+ list.get(3) + ":" + list.get(4) + ":" + "00";
	}



	@Override
	protected String getHead() {
		return "/events/event";
	}

	protected String getTitle(Node currentEvent) {
		return getTagValue(currentEvent, "summary/text()");
	}

	protected String getSummary(Node currentEvent) {
		return getTagValue(currentEvent, "description/text()");
	}

	protected String getURL(Node currentEvent) {
		return getTagValue(currentEvent, "link/text()");
	}

	protected String getStartDate(Node currentEvent) {
		ArrayList<String> time = new ArrayList<String>();
		for (String s : myTime)
			time.add(getTagValue(currentEvent, "start/" + s + "/text()"));
		return format(time);
	}

	protected String getEndDate(Node currentEvent) {
		ArrayList<String> time = new ArrayList<String>();
		for (String s : myTime)
			time.add(getTagValue(currentEvent, "end/" + s + "/text()"));
		return format(time);
	}



}