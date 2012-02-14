import java.io.File;
import java.util.*;

import event.*;
import filter.*;
import parser.*;
import writer.*;


public class TivooSystem {
    
    private List<Event> myEventList;
    
    public TivooSystem() {
        myEventList = new ArrayList<Event>();
    }

    public void loadFile (String file) {
        File toParse = new File(file);
        Parser parser = new DukeBasketballParser(toParse);
        parser.parse();
        myEventList = parser.getEventList();
    }
    
    public void filterByKeyword (String keyword) {
        Filter filter = new FilterByKeyword(myEventList, keyword);
        filter.filter();
        myEventList = filter.getFilteredList();   
    }
    
    @SuppressWarnings("deprecation")
    public void filterByTimeFrame (String startTime, String endTime) {
        Date start = new Date(startTime);
        Date end = new Date(endTime);
        Filter filter = new FilterByTime(myEventList, start, end);
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
