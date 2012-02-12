package filter;

import java.util.*;
import event.*;

public class FilterByKeyword extends Filter{
    
    private List<Event> myList;
    private String myKeyword;
    
    public FilterByKeyword(List<Event> list, String keyword) {
        this.myList = list;
        this.myKeyword = keyword;
    }

    @Override
    protected List<Event> filter() {
        List<Event> toReturn = new ArrayList<Event>();
        for (Event entry: myList) {
            if (entry.containsKeywordInTitle(myKeyword)) {
                toReturn.add(entry);
            }
        }
        return toReturn;
    }
    
    
}
