package filter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import event.Event;
import exception.TivooException;

public class FilterByTime extends Filter{

    private static DateFormat defaultDateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");


    private Date myStartTime, myEndTime;

    public FilterByTime (List<Event> list, String startTime, String endTime)  {
        Date start, end;
        try {
            start = defaultDateFormat.parse(startTime);
            end = defaultDateFormat.parse(endTime);
        } catch (ParseException e) {
            throw new TivooException("Check input date format: must be in forms of 'MM/dd/yy hh:mm:ss a'", 
                    TivooException.Type.BAD_DATEFORMAT);
        }
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        myStartTime = start;
        myEndTime = end;
    }

    @Override
    public void filter() {
        myFilteredList = new ArrayList<Event>();
        for (Event entry: myOriginalList) {
            if (entry.isWithinTimeFrame(myStartTime, myEndTime)) {
                myFilteredList.add(entry);
            }
        }
    }

    @Override
    public List<Event> getFilteredList() {
        return new ArrayList<Event>(myFilteredList);
    }
}
