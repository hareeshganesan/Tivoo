package parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import event.Event;

public class DukeCalendarParserTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException,
			ParserConfigurationException, SAXException {
		List<Event> eventList;
		Parser test = new DukeCalendarParser();
		test.parse("./dukecal.xml");
		eventList = test.getEventList();
		FileWriter fw = new FileWriter("./test.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (Event event : eventList) {
			String output = "----------------------------\n";
			output += "Titile= " + event.getMyTitle() + "\n";
			output += "StartTime= " + event.getMyStart().toGMTString() + "   "
					+  event.getMyStart().toLocaleString()+"\n";
			output += "EndTime= " + event.getMyEnd() +event.getMyEnd().toString()+"\n";
			output += "Summary= " + event.getMySummary() + "\n";
			bw.write(output);
		}

		bw.flush();
		bw.close();
		fw.close();
	}
}