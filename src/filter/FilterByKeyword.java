package filter;

import java.util.*;

import event.*;

public class FilterByKeyword extends Filter{
    
    private String myKeyword;
    
    public FilterByKeyword(List<Event> list, String keyword) {
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        myKeyword = keyword;
    }

    @Override
    public void filter() {
        myFilteredList = new ArrayList<Event>();
        for (Event entry: myOriginalList) {
            if (entry.containsKeywordInTitle(myKeyword)) {
                myFilteredList.add(entry);
            }
        }
    }

    @Override
    public List<Event> getFilteredList() {
        return new ArrayList<Event>(myFilteredList);
    }
    
}
