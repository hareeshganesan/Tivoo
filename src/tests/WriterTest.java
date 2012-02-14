package tests;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import event.Event;
import writer.SummaryWriter;

public class WriterTest
{

    public static void main (String[] args)
    {
        List<Event> events = new ArrayList<Event>();
        for(int i=0; i<10; i++){
            events.add(new Event("title"+i, "summary"+i, Calendar.getInstance().getTime(), Calendar.getInstance().getTime()));
        }
        (new SummaryWriter()).outputHTML(events);
    }

}
