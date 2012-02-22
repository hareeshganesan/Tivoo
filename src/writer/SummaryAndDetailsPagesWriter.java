package writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.hp.gagawa.java.elements.*;
import event.Event;


public class SummaryAndDetailsPagesWriter extends Writer
{

    public SummaryAndDetailsPagesWriter (String directory)
    {
        setMyTitle("Summary and Details");
        setMyDirectory(directory);
    }


    public void outputHTML (List<Event> events)
    {
        String directory = getMyDirectory();
        outputSummaryPage(events, directory);
        String s[] = directory.split("/");
        String ss = directory.substring(0, directory.indexOf(s[s.length - 1]));
        outputDetailsPages(events, ss);
    }


    public void outputDetailsPages (List<Event> events, String directory)
    {
        for (Event event : events)
        {
            Html html = initializeHTMLDocument();

            html.appendChild(new H2().appendChild(new Text(event.get("title"))));

            Table details = new Table();

            generateDetail(details, "Start Time", event.get("startTime"));
            generateDetail(details, "End Time", event.get("endTime"));
            generateDetail(details, "Summary", event.get("summary"));

            String filename =
                directory + "event" + events.indexOf(event) + ".html";

            html.appendChild(details);
            write(html, filename);
        }
    }


    private void generateDetail (Table details, String eventType, String detail)
    {
        String fullDetail = eventType + ": " + detail;
        details.appendChild(new Tr().appendChild(new Td().appendChild(new Text(fullDetail))));
    }


    public void outputSummaryPage (List<Event> events, String filename)
    {
        Html html = initializeHTMLDocument();

        String[] days =
            {
                    "Sunday",
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday" };

        HashMap<Integer, ArrayList<Event>> eventsByDate = groupByDate(events);
        for (int i = 0; i < 7; i++)
        {
            ArrayList<Event> day = eventsByDate.get(i);
            Table table = new Table();
            for (Event event : day)
            {
                Tr event_format = new Tr();
                A link = new A();

                String linkText = "event" + events.indexOf(event) + ".html";
                link.setHref(linkText);
                link.appendChild(new Text(event.get("title")));

                event_format.appendChild((new Td()).appendChild(link));
                event_format.appendChild((new Td()).appendChild(new Text(event.get("startTime")
                                                                              .toString())));
                event_format.appendChild((new Td()).appendChild(new Text(event.get("endTime")
                                                                              .toString())));

                table.appendChild(event_format);
            }
            Text dayOfTheWeek = new Text(days[i]);
            html.appendChild(new H2().appendChild(dayOfTheWeek));
            html.appendChild(table);
        }
        write(html, filename);
    }


    @SuppressWarnings("deprecation")
    private HashMap<Integer, ArrayList<Event>> groupByDate (List<Event> events)
    {
        HashMap<Integer, ArrayList<Event>> dateSet =
            new HashMap<Integer, ArrayList<Event>>();
        for (int i = 0; i < 7; i++)
            dateSet.put(i, new ArrayList<Event>());

        SimpleDateFormat dateCreator = new SimpleDateFormat(Event.dateFormat);
        for (Event event : events)
        {
            try
            {
                dateSet.get(dateCreator.parse(event.get("startTime")).getDay())
                       .add(event);
            }
            catch (ParseException e)
            {}
        }
        return dateSet;
    }

}
