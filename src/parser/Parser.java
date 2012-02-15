package parser;

import java.io.File;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

import event.Event;

public abstract class Parser {
	private ArrayList<Event> myEvents;
	private Document myDocument;


	public void parse(String file) {
		File toParse = new File(file);
		myEvents = new ArrayList<Event>();
		myDocument = generateDocument(toParse);
		parse(myEvents, myDocument);
	}

	public abstract void parse(ArrayList<Event> myEvents, Document myDocument);

	public List<Event> getEventList() {
		return new ArrayList<Event>(myEvents);
	}

	protected Document generateDocument(File file) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document toReturn = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			toReturn = dBuilder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		toReturn.getDocumentElement().normalize();
		return toReturn;
	}

	protected NodeList getTagNodes(Object Node, String xPath) {
		XPathExpression expr = getXPathExpression(xPath);
		NodeList nodeList = null;
		try {
			nodeList = (NodeList) expr.evaluate(Node, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	protected String getTagValue(Object Node, String xPath) {
		Node nodeGot = getTagNodes(Node, xPath).item(0);
		if (nodeGot != null)
			return nodeGot.getNodeValue();
		else
			return "";
	}

	protected XPathExpression getXPathExpression(String xPath) {
		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath myXpath = xpathfactory.newXPath();
		XPathExpression toReturn = null;
		try {
			toReturn = myXpath.compile(xPath);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@SuppressWarnings("deprecation")
	protected Date getDateFromString(String in) {
		Date toReturn = null;
		toReturn = new Date(in);
		// try {
		// toReturn = DateFormat.getInstance().parse(in);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return toReturn;
	}

}