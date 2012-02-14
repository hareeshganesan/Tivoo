import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Main
{
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException, DOMException, ParseException
    {        
        TivooSystem s = new TivooSystem();
        s.loadFile("DukeBasketBall.xml");
        s.filterByKeyword("vs");
        //s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");
    }

}
