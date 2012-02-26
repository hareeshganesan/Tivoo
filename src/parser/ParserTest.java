package parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import event.Event;


public class ParserTest
{
    public static void main (String[] args) throws IOException
    {
        //put everything out in EventList after parsing a certain file in Test.text
        // to test change the code between ////////
        FileWriter fw = new FileWriter("Test.txt");

        /////////////////////////////////////////
        Parser p = new GoogleCalendarParserChen();
        File file = new File("./xml/googlecal.xml");
        ////////////////////////////////////////

        p.loadFile(file);
        p.parse();
        ArrayList<Event> list = (ArrayList<Event>) p.getEventList();
        for (Event event : list)
        {
            String out = "Title: " + event.get("title") + "\n";
            out += "Summary: " + event.get("summary") + "\n";
            out += "Url: " + event.get("url") + "\n";
            out += "StartTime: " + event.get("startTime") + "\n";
            out += "endTime: " + event.get("endTime") + "\n";
            out += "repeat: "+event.get("repeat")+"\n------------------------------------\n";
           
            fw.write(out);
            fw.flush();
        }
        fw.close();
    }
}
