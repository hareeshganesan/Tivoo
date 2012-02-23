package filter;

import java.util.*;

import event.*;

public class FilterByKeyword extends FilterDecorator{
    
    private String myKeyword;
    
    public FilterByKeyword(String keyword) {
        super();
        myKeyword = keyword;
    }

    @Override
    public void filter(List<Event> list) {
        List<Event> decoratedList = decoratedFilterWork(list);
        for (Event entry: decoratedList) {
            if (entry.containsKeyWord("title", myKeyword)) {
                myFilteredList.add(entry);
            }
        }
    }
}
