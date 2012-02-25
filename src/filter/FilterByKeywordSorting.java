package filter;

import java.util.*;

import event.*;
import exception.TivooEventKeywordNotFound;

public class FilterByKeywordSorting extends FilterDecorator{

    private String myKey;

    public FilterByKeywordSorting(String key) {
        super();
        myKey = key;
    }

    @Override
    public void filter(List<Event> list) {
        List<Event> decoratedList = decoratedFilterWork(list);
        List<Event> eventsThatContainThisKey = new ArrayList<Event>();
        List<Event> eventsThatDontContainThisKey = new ArrayList<Event>();
        for (Event event: decoratedList) {
            try {
                event.get(myKey);
                eventsThatContainThisKey.add(event);
            } catch (TivooEventKeywordNotFound e) {
                eventsThatDontContainThisKey.add(event);
            }
        }
        Collections.sort(eventsThatContainThisKey, new Comparator<Event>() {
            public int compare(Event e1, Event e2) {
                return e1.get(myKey).compareTo(e2.get(myKey));
            }
        });
        myFilteredList.addAll(eventsThatContainThisKey);
        myFilteredList.addAll(eventsThatDontContainThisKey);
    }

}
