package filter;

import java.util.*;

import event.Event;

public class FilterByTime extends Filter{
    
    private List<Event> myOriginalList, myFilteredList;
    private Date myStartTime, myEndTime;
    
    public FilterByTime (List<Event> list, Date startTime, Date endTime) {
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        this.myStartTime = startTime;
        this.myEndTime = endTime;
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
