import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import parser.BBallParser;


public class Main
{
    public static void main (String[] args)
        throws ParserConfigurationException,
            SAXException,
            IOException,
            DOMException,
            ParseException
    {

        BBallParser parser = new BBallParser("DukeBasketBall.xml");
        parser.parse();
    }

}
