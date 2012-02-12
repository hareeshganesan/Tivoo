package parser;

import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import event.Event;


public class BBallParser
{
    private org.w3c.dom.Document myDoc;


    /**
     * Fix this throwing
     * 
     * @param in
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public BBallParser (File in)
        throws ParserConfigurationException,
            SAXException,
            IOException
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        myDoc = dBuilder.parse(in);

    }


    public List<Event> parse () throws DOMException, ParseException
    {
        ArrayList<Event> events = new ArrayList<Event>();

        NodeList nList = myDoc.getElementsByTagName("Calendar");
        for (int i = 0; i < nList.getLength(); i++)
        {
            events.add(createEvent(nList.item(i)));

        }

        return events;

    }



    private Event createEvent (Node item) throws DOMException, ParseException
    {
        String title = null;
        String summary = null;
        Date start = null;
        Date end = null;
        String startString="";
        String endString="";
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        


        NodeList children = item.getChildNodes();
        for (int i = 0; i < children.getLength(); i++)
        {

            if (children.item(i).getNodeName().equals("Subject"))
            {

                title = children.item(i).getTextContent();

            }

            if (children.item(i).getNodeName().equals("Description"))
            {
                summary = children.item(i).getTextContent();
            }

            if (children.item(i).getNodeName().equals("StartDate"))
            {
                startString+=children.item(i).getTextContent();

            }
            if (children.item(i).getNodeName().equals("StartTime"))
            {
               startString+=" "+children.item(i).getTextContent();
               start = df.parse(startString);
               

            }
            if (children.item(i).getNodeName().equals("EndDate"))
            {
                endString+=children.item(i).getTextContent();


            }
            if (children.item(i).getNodeName().equals("EndTime"))
            {
                endString+=" "+children.item(i).getTextContent();
                end = df.parse(endString);
                

            }

        }

        return new Event(title, summary, start, end);
        
    }

}