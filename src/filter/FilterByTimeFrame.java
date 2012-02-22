package filter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import event.Event;
import exception.*;

public class FilterByTimeFrame extends Filter{

    private static String defaultDateFormatString = "MM/dd/yy hh:mm:ss a";
    private static DateFormat defaultDateFormat = new SimpleDateFormat(defaultDateFormatString);

    private Date myStartTime, myEndTime;

    public FilterByTimeFrame (String startTime, String endTime) {
        Date start, end;
        try {
            start = defaultDateFormat.parse(startTime);
            end = defaultDateFormat.parse(endTime);
        } catch (ParseException e) {
            throw new TivooIllegalDateFormat();
        }
        myOriginalList = new ArrayList<Event>();
        myFilteredList = new ArrayList<Event>();
        myStartTime = start;
        myEndTime = end;
    }

    @Override
    public void filter(List<Event> list) {
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        for (Event entry: myOriginalList) {
            if (entry.isWithinTimeFrame(myStartTime, myEndTime)) {
                myFilteredList.add(entry);
            }
        }
        nextFilter();
    }    
    
    public static String getDefaultDateFormat() {
        return defaultDateFormatString;
    }
    

    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
        Date eventStartTime=(Date) myField.get(Type.START);
        Date eventEndTime= (Date) myField.get(Type.END);
        return (eventStartTime.before(endTime) && eventEndTime.after(startTime));
    }

}
