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
import exception.TivooException;

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
            toReturn.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            System.err.println("ARE YOU KIDDING ME");
        } catch (SAXException e) {   // why SAXException|IOException doesn't work?
            throw new TivooException("Check input directory dude", TivooException.Type.BAD_INPUTDIRECTORY);
        } catch (IOException e) {
            throw new TivooException("Check input directory dude", TivooException.Type.BAD_INPUTDIRECTORY);
        }
        return toReturn;
    }

    protected NodeList getTagNodes(Object Node, String xPath) {
        XPathExpression expr = getXPathExpression(xPath);
        NodeList nodeList;
        try {
            nodeList = (NodeList) expr.evaluate(Node, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new TivooException("No such tags", TivooException.Type.BAD_PARSING);
        }
        return nodeList;
    }

    protected String getTagValue(Object Node, String xPath) {
        String toReturn;
        try {
            toReturn = getTagNodes(Node, xPath).item(0).getNodeValue();
        } catch (NullPointerException e) {
            throw new TivooException("null value in Tags", TivooException.Type.BAD_PARSING);
        }
        return toReturn;
    }

    private XPathExpression getXPathExpression(String xPath) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath myXpath = xpathfactory.newXPath();
        XPathExpression toReturn;
        try {
            toReturn = myXpath.compile(xPath);
        } catch (XPathExpressionException e) {
            throw new TivooException("xPath invalid", TivooException.Type.BAD_PARSING);
        }
        return toReturn;
    }

    protected Date getDateFromString(String in, DateFormat dateFormat) {
        Date toReturn;
        try {
            toReturn = dateFormat.parse(in);
        } catch (ParseException e) {
        	System.out.println("bad time string to parse! "+ in);
            throw new TivooException("parse time failure", TivooException.Type.BAD_PARSING);
        }
        return toReturn;
    }

}