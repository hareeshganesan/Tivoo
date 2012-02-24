package writer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import event.Event;
import writer.Writer;


public class ConflictWriter extends Writer
{
    public ConflictWriter (String filename)
    {
        setMyDirectory(filename);
        setMyTitle("Conflict List");
    }


    @SuppressWarnings("unchecked")
    @Override
    public void outputHTML (List<Event> events)
    {

        Html html = initializeHTMLDocument();

        Table table = new Table();
        for (Event event : events)
        {
            if (hasConflict(event, events))
            {
                Tr event_format = new Tr();

                event_format.appendChild((new Td()).appendChild(new Text(event.get("title"))));
                event_format.appendChild((new Td()).appendChild(new Text(event.get("startTime")
                                                                              .toString())));
                event_format.appendChild((new Td()).appendChild(new Text(event.get("endTime")
                                                                              .toString())));

                table.appendChild(event_format);
            }
        }
        write(html, getMyDirectory());
    }


    private boolean hasConflict (Event event, List<Event> events)
    {
        DateFormat format = new SimpleDateFormat(Event.dateFormat);
        try
        {
            Date start = format.parse(event.get("startTime"));
            Date end = format.parse(event.get("endTime"));
            for(Event other : events){
                if( other != event){
                    Date otherStart = format.parse(other.get("startTime"));
                    Date otherEnd = format.parse(other.get("endTime"));
                    if(end.after(otherStart) || start.before(otherEnd))
                        return true;
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
