package parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import event.Event;

public class DukeCalParser  {
	private ArrayList<Event> myEvents = new ArrayList<Event>();
	private Document doc;

	public DukeCalParser(String file) throws ParserConfigurationException,
			SAXException, IOException {

		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
	}

	public List<Event> parse() {
		try {

			XPathExpression expr = getXPathExpression("/events/event");
			NodeList eventList = (NodeList) expr.evaluate(doc,
					XPathConstants.NODESET);

			for (int temp = 0; temp < eventList.getLength(); temp++) {
				String title;
				String summary;
				Node currentNode = eventList.item(temp);
				title = getTagValue(currentNode, "summary/text()");
				summary = getTagValue(currentNode,
						"description/text()");
				myEvents.add(new Event(title, summary, startTime(currentNode),
						endTime(currentNode)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myEvents;
	}

	
    private String getTagValue(Node node, String xPath)
			throws XPathExpressionException {

		XPathExpression expr = getXPathExpression(xPath);
		NodeList eventList = (NodeList) expr.evaluate(node,
				XPathConstants.NODESET);
		return eventList.item(0).getNodeValue();
	}

	private  XPathExpression getXPathExpression(String xPath)
			throws XPathExpressionException {
		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath myXpath = xpathfactory.newXPath();
		return myXpath.compile(xPath);
	}
//10,11,12,13

	@SuppressWarnings({ "deprecation", "static-access" })
    private  Date startTime(Node node) throws XPathExpressionException {
		Date starttime = new Date();
		String date = getTagValue(node, "start/longdate/text()") + " ";
		
			String time=getTagValue(node, "start/utcdate/text()").substring(9, 11)+":"+
					getTagValue(node, "start/utcdate/text()").substring(11, 13);
			
		starttime.setTime(starttime.parse(date+time));
		return starttime;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private  Date endTime(Node node) throws XPathExpressionException {
		Date endtime = new Date();
		String date = getTagValue(node, "end/longdate/text()") + " ";
		
		String time=getTagValue(node, "end/utcdate/text()").substring(9, 11)+":"+
				getTagValue(node, "end/utcdate/text()").substring(11, 13);
		endtime.setTime(endtime.parse(date+time));
		return endtime;
	}
}