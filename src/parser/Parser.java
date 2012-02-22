package parser;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import event.Event;
import exception.*;

public abstract class Parser {
    protected ArrayList<Event> myEvents;
    protected Document myDocument;

    public void loadFile(File file) {
        myDocument = generateDocument(file);
    }

    protected abstract String getHead();
    protected abstract String getTitle(Node currentEvent);
    protected abstract String getSummary(Node currentEvent);
    protected abstract String getURL(Node currentEvent);
    protected abstract Date getStartDate(Node currentEvent);
    protected abstract Date getEndDate(Node currentEvent);

    public abstract Parser newParser();
    
    public void parse() {
        if (myDocument == null) {
            return;
        }
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
        HashMap<Event.Type,Object> map= new HashMap<Event.Type,Object>();
        map.put(Event.Type.TITLE, title);
        map.put(Event.Type.SUMMARY, summary);
        map.put(Event.Type.URL, url);
        map.put(Event.Type.START, start);
        map.put(Event.Type.END, end);
        return new Event(map);
    }

    public List<Event> getEventList() {
        return new ArrayList<Event>(myEvents);
    }

    private Document generateDocument(File file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document toReturn = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            toReturn = dBuilder.parse(file);
            toReturn.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            throw new TivooInternalParsingError("DocumentBuilderFactory initialization failed");
        } catch (SAXException e) {   // why SAXException|IOException doesn't work?
            throw new TivooInvalidFeed();
        } catch (IOException e) {
            throw new TivooInvalidFeed();
        }
        return toReturn;
    }

    protected NodeList getTagNodes(Object Node, String xPath) {
        XPathExpression expr = getXPathExpression(xPath);
        NodeList nodeList;
        try {
            nodeList = (NodeList) expr.evaluate(Node, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new TivooInternalParsingError("getTagNodes failed");
        }
        return nodeList;
    }

    protected String getTagValue(Object Node, String xPath) {
        Node node = getTagNodes(Node, xPath).item(0);
        if (node == null) {
            return "";
        }
        else {
            return node.getNodeValue();
        }
    }

    private XPathExpression getXPathExpression(String xPath)  {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath myXpath = xpathfactory.newXPath();
        XPathExpression toReturn;
        try {
            toReturn = myXpath.compile(xPath);
        } catch (XPathExpressionException e) {
            throw new TivooInternalParsingError("xPath complication failed");
        }
        return toReturn;
    }

    protected Date getDateFromString(String in, DateFormat dateFormat) {
        Date toReturn;
        try {
            toReturn = dateFormat.parse(in);
        } catch (ParseException e) {
            throw new TivooInternalParsingError("DateFormat invalid");
        }
        return toReturn;
    }

}