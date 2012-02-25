package parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import event.Event;
import exception.*;
 

public abstract class Parser
{
    protected ArrayList<Event> myEvents;
    protected Document myDocument;

	public Parser() {
		myDocument = null;
		myEvents = new ArrayList<Event>();
	}
    public void loadFile (File file)
    {
        myDocument = generateDocument(file);
    }


    protected abstract String getHead ();


    protected  HashMap<String, String> getMyFields (Node currentEvent){
		HashMap<String, String> toReturn = new HashMap<String, String>();
		toReturn.put("title", getTitle(currentEvent));
		toReturn.put("summary", getSummary(currentEvent));
		toReturn.put("startTime", getStartDate(currentEvent));
		toReturn.put("endTime", getEndDate(currentEvent));
		toReturn.put("url", getURL(currentEvent));
		return toReturn;
    }


    public void parse ()
    {
        if (myDocument == null)
        {
            return;
        }
        NodeList eventList = getTagNodes(myDocument, getHead());
        for (int temp = 0; temp < eventList.getLength(); temp++)
        {
            Node currentEvent = eventList.item(temp);
            
            myEvents.add(createEvent(currentEvent));
        }
    }


    private Event createEvent (Node currentEvent)
    {

        HashMap<String, String> eventFields = getMyFields(currentEvent);
  
        return new Event(eventFields);
    }


    public List<Event> getEventList ()
    {
        return new ArrayList<Event>(myEvents);
    }


    private Document generateDocument (File file)
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document toReturn = null;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            toReturn = dBuilder.parse(file);
            toReturn.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException e)
        {
            throw new TivooSystemError("DocumentBuilderFactory initialization failed");
        }
        catch (SAXException e)
        { // why SAXException|IOException doesn't work?
            throw new TivooInvalidFeed();
        }
        catch (IOException e)
        {
            throw new TivooInvalidFeed();
        }
        return toReturn;
    }


    protected NodeList getTagNodes (Object Node, String xPath)
    {
        XPathExpression expr = getXPathExpression(xPath);
        NodeList nodeList;
        try
        {
            nodeList = (NodeList) expr.evaluate(Node, XPathConstants.NODESET);
        }
        catch (XPathExpressionException e)
        {
            throw new TivooSystemError("getTagNodes failed");
        }
        return nodeList;
    }


    protected String getTagValue (Object Node, String xPath)
    {
        Node node = getTagNodes(Node, xPath).item(0);
        if (node == null)
        {
            return "";
        }
        else
        {
            return node.getNodeValue();
        }
    }


    private XPathExpression getXPathExpression (String xPath)
    {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath myXpath = xpathfactory.newXPath();
        XPathExpression toReturn;
        try
        {
            toReturn = myXpath.compile(xPath);
        }
        catch (XPathExpressionException e)
        {
            throw new TivooSystemError("xPath complication failed");
        }
        return toReturn;
    }

    protected String getTitle (Node currentEvent)
    {
        return "Null Such Field";
    }


    protected String getSummary (Node currentEvent)
    {
    	return "Null Such Field";
    }


    protected String getURL (Node currentEvent)
    {
    	return "Null Such Field";
    }



    protected String getStartDate (Node currentEvent)
    {
    	return "Null Such Field";
    }


    protected String getEndDate (Node currentEvent)
    {
    	return "Null Such Field";
    }



}
