package filter;

import java.util.*;

import event.Event;

public class FilterByTime extends Filter{
    
    private List<Event> myOriginalList, myFilteredList;
    private Date myStartTime, myEndTime;
    
    @SuppressWarnings("deprecation")
    public FilterByTime (List<Event> list, String startTime, String endTime) {
        Date start = new Date();
        Date end = new Date();
        start.setTime(Date.parse(startTime));
        end.setTime(Date.parse(endTime));
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
