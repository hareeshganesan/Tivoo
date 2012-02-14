package filter;

import java.util.*;
import event.*;

public abstract class Filter {
    
    public abstract void filter();
    
    public abstract List<Event> getFilteredList();
}
