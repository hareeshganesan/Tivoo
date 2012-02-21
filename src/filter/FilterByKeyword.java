package filter;

import java.util.*;

import event.*;

public class FilterByKeyword extends Filter{
    
    private String myKeyword;
    
    public FilterByKeyword(String keyword) {
        myOriginalList = new ArrayList<Event>();
        myFilteredList = new ArrayList<Event>();
        myKeyword = keyword;
    }

    @Override
    public void filter(List<Event> list) {
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        for (Event entry: myOriginalList) {
            if (entry.containsKeyword("title",myKeyword)) {
                myFilteredList.add(entry);
            }
        }
        nextFilter();
    }
}
