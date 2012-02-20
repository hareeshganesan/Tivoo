package filter;

import java.util.*;

import event.*;

public abstract class Filter {
    protected List<Event> myOriginalList, myFilteredList;
    protected Filter myNextFilter;

    public abstract void filter(List<Event> list);

    protected void nextFilter() {
        if (myNextFilter != null) {
            myNextFilter.filter(myFilteredList);
            myFilteredList = myNextFilter.getFilteredList();
        }
    }
            
    public List<Event> getFilteredList() {
        return new ArrayList<Event>(myFilteredList);
    }
    
    public List<Event> getOriginalLst() {
        return new ArrayList<Event>(myOriginalList);
    }
    
    public Filter getNextFilter() {
        return myNextFilter;
    }
    
    public void setNextFilter(Filter filter) {
        myNextFilter = filter;
    }
    
    public void setFilterAtBottom(Filter filter) {
        if (myNextFilter == null) {
            myNextFilter = filter;
        }
        else {
            myNextFilter.setFilterAtBottom(filter);
        }
    }
}