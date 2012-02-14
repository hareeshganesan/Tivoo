package filter;

import java.util.*;

import event.Event;

public class FilterByTime extends Filter{
    
    private List<Event> myList;
    private Date myStartTime, myEndTime;
    
    public FilterByTime (List<Event> list, Date startTime, Date endTime) {
        this.myList = list;
        this.myStartTime = startTime;
        this.myEndTime = endTime;
    }

    @Override
    protected List<Event> filter() {
        List<Event> toReturn = new ArrayList<Event>();
        for (Event entry: myList) {
            if (entry.isWithinTimeFrame(myStartTime, myEndTime)) {
                toReturn.add(entry);
            }
        }
        return toReturn;
    }
}
