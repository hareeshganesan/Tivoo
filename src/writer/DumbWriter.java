package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Node;
import com.hp.gagawa.java.elements.Html;
import event.Event;

public class DumbWriter extends Writer
{
    public DumbWriter (String filename)
    {
        setMyDirectory(filename);
        
    }
    @Override
    public void outputHTML (List<Event> events)
    {
        
        
        try
        {
            FileWriter fw = new FileWriter(getMyDirectory());
            fw.append("<html>\n<body>\n");
            fw.append("<h1> Dumb View Just Shows Titles </h1>");
            fw.append("\n");
            fw.append("<p>");
            for(Event event : events){
                fw.append("<p>");
                String title =event.get("title");
                fw.append(title);
                fw.append("</p>");
            }
            fw.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
