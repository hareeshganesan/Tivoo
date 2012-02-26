package parser;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import exception.TivooInvalidFeed;
import exception.TivooSystemError;


public abstract class Parser
{
    protected ArrayList<Event> myEvents;
    protected Document myDocument;


    public Parser ()
    {
        myDocument = null;
        myEvents = new ArrayList<Event>();
    }


    public void loadFile (File file)
    {
        myDocument = generateDocument(file);
    }


    protected abstract String getHead ();


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

        return new Event(getMyFields(currentEvent));
    }


    protected HashMap<String, String> getMyFields (Node currentEvent)
    {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("title", getTitle(currentEvent));
        fields.put("summary", getSummary(currentEvent));
        fields.put("url", getURL(currentEvent));
        fields.put("startTime", getStartDate(currentEvent));
        fields.put("endTime", getEndDate(currentEvent));
        fields.put("repeat", getRepeatPeriod(currentEvent));
        return fields;
    }


    /**
     * Allows for generic events but reduces code duplication so the parser has
     * default behaviour
     * 
     * @param currentEvent
     * @return
     */

    protected String getTitle (Node currentEvent)
    {

        return "no such field";
    }


    protected String getSummary (Node currentEvent)
    {
        return "no such field";
    }


    protected String getURL (Node currentEvent)
    {
        return "no such field";
    }


    protected String getStartDate (Node currentEvent)
    {
        return "no such field";
    }


    protected String getEndDate (Node currentEvent)
    {
        return "no such field";
    }
    
    protected String getRepeatPeriod (Node currentEvent)
    {
        return "none";
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


    protected String reformatDateString (String info, String oldFormat)
    {
        DateFormat df = new SimpleDateFormat(oldFormat);
        Date date = new Date();
        DateFormat eventFormat = new SimpleDateFormat(Event.dateFormat);

        try
        {
            date = df.parse(info);
        }
        catch (ParseException e)
        {
            //TODO get a better exception
            e.printStackTrace();
        }

        return eventFormat.format(date);
    }

}
