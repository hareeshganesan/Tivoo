import java.util.*;

import event.*;
import exception.TivooException;
import filter.*;
import parser.*;
import writer.*;


public class TivooSystem {
    
    private List<Event> myEventList;
    private static Map<String,Parser> myMap;
    
    static{
        myMap = new HashMap<String,Parser>();
    	myMap.put("DukeBasketBall.xml", new DukeBasketballParser());
    	myMap.put("dukecal.xml", new DukeCalendarParser());
    }
    
    public TivooSystem() {
        myEventList = new ArrayList<Event>();
    }

    public void loadFile (String file) {
        String[] s = file.split("/");
        String key = s[s.length-1];
        Parser parser = myMap.get(key);
        try {
            parser.parse(file);
        }
        catch (NullPointerException e) {
            throw new TivooException("Never seen this xml before", TivooException.Type.BAD_INPUTDIRECTORY);
        }
        myEventList = parser.getEventList();
    }
    
    public void filterByKeyword (String keyword) {
        Filter filter = new FilterByKeyword(myEventList, keyword);
        filter.filter();
        myEventList = filter.getFilteredList();   
    }
    
    public void filterByTimeFrame (String startTime, String endTime) {
        Filter filter = new FilterByTime(myEventList, startTime, endTime);
        filter.filter();
        myEventList = filter.getFilteredList();
    }

    public void  outputDetails(String outputLocation) {
        Writer writer = new DetailsWriter();
        writer.outputHTML(myEventList, outputLocation);
    }
     
    public void outputSummary(String outputLocation) {
        Writer writer = new SummaryWriter();
        writer.outputHTML(myEventList, outputLocation);
    }
    
    public List<Event> getEventList() {
        return new ArrayList<Event>(myEventList);
    }
}
