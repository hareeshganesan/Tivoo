package filter;

import java.util.*;
import event.*;

public abstract class Filter {
	protected List<Event> myOriginalList, myFilteredList;
    
    public abstract void filter();
    
    public abstract List<Event> getFilteredList();
}
