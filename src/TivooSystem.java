import java.io.File;
import java.util.*;

import event.*;
import exception.TivooException;
import filter.*;
import parser.*;
import writer.*;


public class TivooSystem {
    
    private List<Event> myOriginalList;
    private List<Event> myFilteredList;
    private List<Writer> myWriters;
    private Filter myHeadFilter;
    private static Map<String,Parser> myMap = new HashMap<String,Parser>();

    static{
    	myMap.put("DukeBasketBall.xml", new DukeBasketballParser());
    	myMap.put("dukecal.xml", new DukeCalendarParser());
    }
    
    public TivooSystem() {
        myOriginalList = new ArrayList<Event>();
        myFilteredList = new ArrayList<Event>();
        myWriters = new ArrayList<Writer>();
        myHeadFilter = null;
    }

    public void loadFile (File file) {
        Parser parser = myMap.get(file.getName());
        try {
            parser.parse(file);
        }
        catch (NullPointerException e) {
            throw new TivooException("Never seen this xml before", TivooException.Type.BAD_INPUTDIRECTORY);
        }
        myOriginalList = parser.getEventList();
    }
    
    public void addFilterByKeyword (String keyword) {
        Filter filter = new FilterByKeyword(keyword);
        addFilter(filter);
    }
    
    public void addFilterByTimeFrame (String startTime, String endTime) {
        Filter filter = new FilterByTimeFrame(startTime, endTime);
        addFilter(filter);
    }

    private void addFilter(Filter filter) {
        if (myHeadFilter == null) {
            myHeadFilter = filter;
        }
        else {
//            filter.setNextFilter(myHeadFilter);
//            myHeadFilter = filter;
            myHeadFilter.setFilterAtBottom(filter);
        }
    }

    public void addSummaryAndDetailPagesWriter(String directory) {
        Writer writer = new SummaryAndDetailsPagesWriter(directory);
        addWriter(writer);
    }
    
    private void addWriter(Writer writer) {
        myWriters.add(writer);
    }
    
    public void perform() {
        filter();
        output();
    }
    
    private void filter() {
        myHeadFilter.filter(myOriginalList);
        myFilteredList = myHeadFilter.getFilteredList();
    }
    
    private void output() {
        for (Writer writer:myWriters) {
            writer.outputHTML(myFilteredList);
        }
    }
}
