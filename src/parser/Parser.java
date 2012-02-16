package parser;

import java.io.File;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

import event.Event;

public abstract class Parser {
    protected ArrayList<Event> myEvents;
    protected Document myDocument;

    public void parse(String file) {
        myDocument = generateDocument(file);
        parse();
    }

    protected abstract String getHead();
    protected abstract String getTitle(Node currentEvent);
    protected abstract String getSummary(Node currentEvent);
    protected abstract String getURL(Node currentEvent);
    protected abstract Date getStartDate(Node currentEvent);
    protected abstract Date getEndDate(Node currentEvent);

    private void parse() {
        NodeList eventList = getTagNodes(myDocument, getHead());
        for (int temp = 0; temp < eventList.getLength(); temp++) {
            Node currentEvent = eventList.item(temp);
            myEvents.add(createEvent(currentEvent));
        }
    }
    
    private Event createEvent(Node currentEvent) {
        String title = getTitle(currentEvent);
        String summary = getSummary(currentEvent);
        String url = getURL(currentEvent);
        Date start = getStartDate(currentEvent);
        Date end = getEndDate(currentEvent);
        return new Event(title, summary, start, end, url);
    }

    public List<Event> getEventList() {
        return new ArrayList<Event>(myEvents);
    }

    private Document generateDocument(String input) {
        File file = new File(input);
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

    private XPathExpression getXPathExpression(String xPath) {
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
        Date toReturn = new Date();
        toReturn.setTime(Date.parse(in)) ;
        //		try {
        //		toReturn = DateFormat.getInstance().parse(in);
        //		} catch (Exception e) {
        //		e.printStackTrace();
        //		}
        return toReturn;
    }

}