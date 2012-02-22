import java.io.File;
import java.util.*;

import event.*;
import exception.*;
import filter.*;
import parser.*;
import writer.*;


public class TivooSystem {
    
    private List<Event> myOriginalList;
    private List<Event> myFilteredList;
    private Set<Writer> myWriters;
    private Set<Parser> myParsers;
    private Filter myHeadFilter;
    private static Map<String, ParserFactory> myMap = new HashMap<String, ParserFactory>();

    static {
    	myMap.put("DukeBasketBall.xml", DukeBasketballParser.getFactory());
    	myMap.put("dukecal.xml", DukeCalendarParser.getFactory());
    }
    
    public TivooSystem() {
        myOriginalList = new ArrayList<Event>();
        myFilteredList = new ArrayList<Event>();
        myParsers = new HashSet<Parser>();
        myWriters = new HashSet<Writer>();
        myHeadFilter = null;
    }

    public void loadFile (File file) {
        ParserFactory factory = myMap.get(file.getName());
        if (factory == null) {
            throw new TivooUnrecognizedFeed();
        }
        Parser parser = factory.newParser();
        parser.loadFile(file);
        myParsers.add(parser);
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
        parse();
        filter();
        output();
    }
    
    private void parse() {
        if (myParsers.size() == 0) {
            throw new TivooNoParserSelected();
        }
        for (Parser parser: myParsers) {
            parser.parse();
            myOriginalList.addAll(parser.getEventList());
        }
    }
    
    private void filter() {
        if (myHeadFilter == null) {
            throw new TivooNoFilterSelected();
        }
        myHeadFilter.filter(myOriginalList);
        myFilteredList = myHeadFilter.getFilteredList();
    }
    
    private void output() {
        if (myParsers.size() == 0) {
            throw new TivooNoWriterSelected();
        }
        for (Writer writer:myWriters) {
            writer.outputHTML(myFilteredList);
        }
    }
}
