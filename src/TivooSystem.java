import java.io.File;
import java.util.*;

import event.*;
import filter.*;
import parser.*;


public class TivooSystem {
    
    List<Event> myEventList;
    
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
     
    public void outputSummaryAndDetailsPages(String summaryOutputAddress, String detailOutputAddress) {
        
    }
}
