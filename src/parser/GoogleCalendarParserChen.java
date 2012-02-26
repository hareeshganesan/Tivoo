package parser;

import java.util.HashMap;

import org.w3c.dom.Node;

public class GoogleCalendarParserChen extends Parser {
	private static HashMap<String, String> myMonth = new HashMap<String, String>();
	static {
		myMonth.put("jan", "01");
		myMonth.put("feb", "02");
		myMonth.put("mar", "03");
		myMonth.put("apr", "04");
		myMonth.put("may", "05");
		myMonth.put("jun", "06");
		myMonth.put("jul", "07");
		myMonth.put("aug", "08");
		myMonth.put("sep", "09");
		myMonth.put("oct", "10");
		myMonth.put("nov", "11");
		myMonth.put("dec", "12");
	}

	@Override
	protected String getHead() {
		// TODO Auto-generated method stub
		return "/feed/entry";
	}

	protected String getTitle(Node currentEvent) {
		return getTagValue(currentEvent, "title/text()");
	}

	protected String getURL(Node currentEvent) {
		return getTagValue(currentEvent, "link[1]/attribute::href");
	}

	protected String getStartDate(Node currentEvent) {
		String toparse=getTagValue(currentEvent, "summary/text()");
		return parsetime(toparse,"start");
	}
	
	protected String getEndDate(Node currentEvent) {
		String toparse=getTagValue(currentEvent, "summary/text()");
		return parsetime(toparse,"end");
	}

	private String parsetime(String s,String startorend) {
		int offset=5;
		if (startorend.equalsIgnoreCase("end"))
			offset+=2;
		int start = s.indexOf(":");
		int end = s.indexOf("&");
		s = s.substring(start + 1, end);
		s = s.replaceAll(",", "");
		String[] timeElement = s.split(" ");
     return timeElement[4] + "-" + myMonth.get(timeElement[2].toLowerCase()) + "-"
				+ twodigitnumber(timeElement[3]) + " " + time(timeElement[offset]);
	}

	private String twodigitnumber(String number) {
		if (number.length() < 2)
			return "0" + number;
		return number;
	}

	private String time(String s) {
		String[] hourminute = new String[2];
		int offset = 0;
		if (s.contains("pm")) {
			offset += 12;
			s = s.replaceAll("pm", "");
		} else {
			s = s.replaceAll("am", "");
		}
		if (s.contains(":")) {
			String[] tmp = s.split(":");
			int hour = Integer.parseInt(tmp[0]) + offset;
			hourminute[0] = twodigitnumber("" + hour);
			hourminute[1] = twodigitnumber(tmp[1]);
		} else {
			int hour = Integer.parseInt(s) + offset;
			hourminute[0] = twodigitnumber("" + hour);
			hourminute[1] = "00";
		}
		return hourminute[0] + ":" + hourminute[1] + ":00";
	}
}