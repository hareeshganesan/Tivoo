package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import event.Event;

public class DukeCalParser extends Parser {
	private ArrayList<Event> myEvents = new ArrayList<Event>();

	@SuppressWarnings("deprecation")
	public ArrayList<Event> parse(String file) {
		try {
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			XPathFactory xpathfactory = XPathFactory.newInstance();
			XPath myXpath = xpathfactory.newXPath();
			XPathExpression expr = myXpath.compile("/events/event");
			NodeList eventList = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);

			for (int temp = 0; temp < eventList.getLength(); temp++) {
				Date startTime = new Date();
				Date endTime = new Date();
				String title;
				String summary;
				String time;
				time = getTagValue(eventList.item(temp),
						"start/longdate/text()")
						+ " "
						+ getTagValue(eventList.item(temp), "start/time/text()");
				startTime.setTime(startTime.parse(time));
				time = time = getTagValue(eventList.item(temp),
						"end/longdate/text()")
						+ " "
						+ getTagValue(eventList.item(temp), "end/time/text()");
				endTime.setTime(endTime.parse(time));
				title = getTagValue(eventList.item(temp), "summary/text()");
				summary = getTagValue(eventList.item(temp),
						"description/text()");
				myEvents.add(new Event(title, summary, startTime, endTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myEvents;
	}

	private static String getTagValue(Object Node, String xpath)
			throws XPathExpressionException {
		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath myXpath = xpathfactory.newXPath();
		XPathExpression expr = myXpath.compile(xpath);
		NodeList eventList = (NodeList) expr.evaluate(Node,
				XPathConstants.NODESET);
		return eventList.item(0).getNodeValue();
	}
}