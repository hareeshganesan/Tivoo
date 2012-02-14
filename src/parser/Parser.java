package parser;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import event.Event;


public abstract class Parser
{
    private List<Event> myEvents;
    private Document myDoc;


    public Parser (String fileName)
        throws SAXException,
            IOException,
            ParserConfigurationException
    {
        File toParse = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        myDoc = dBuilder.parse(toParse);
        myDoc.getDocumentElement().normalize();

    }


    public List<Event> parse ()
    {
        /*
         * Find root node of events Loop through all events create each event
         */
        //TODO implement code using Template design
        return myEvents;
    }


    private Event createEvent (Node node)
    {
        /*
         * Using the xpaths for the given file being parsed create event object
         * with relevant information
         */
        //TODO implement code using Template design
        return null;
    }

    //Implement abstract methods allowing parsers to define their set of xpaths

}
