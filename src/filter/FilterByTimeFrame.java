package filter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import event.Event;
import exception.TivooException;

public class FilterByTimeFrame extends Filter{

    private static String defaultDateFormatString = "MM/dd/yy hh:mm:ss a";
    private static DateFormat defaultDateFormat = new SimpleDateFormat(defaultDateFormatString);

    private Date myStartTime, myEndTime;

    public FilterByTimeFrame (String startTime, String endTime)  {
        Date start, end;
        try {
            start = defaultDateFormat.parse(startTime);
            end = defaultDateFormat.parse(endTime);
        } catch (ParseException e) {
            throw new TivooException(String.format("Check input date format: must be in forms of '%s'", defaultDateFormatString), 
                    TivooException.Type.BAD_DATEFORMAT);
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
}
