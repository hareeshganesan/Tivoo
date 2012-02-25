package filter;

import java.util.*;

import event.*;

public abstract class Filter {
    protected List<Event> myOriginalList, myFilteredList;

    public abstract void filter(List<Event> list);

    public List<Event> getFilteredList() {
        return myFilteredList;
    }
    
    public List<Event> getOriginalList() {
        return myOriginalList;
    }
}