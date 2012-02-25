package filter;

import java.util.ArrayList;
import java.util.List;
import event.Event;


public abstract class FilterDecorator extends Filter
{
    protected Filter myDecoratedFilter;


    public FilterDecorator ()
    {
        myOriginalList = new ArrayList<Event>();
        myFilteredList = new ArrayList<Event>();
    }


    protected List<Event> decoratedFilterWork (List<Event> list)
    {
        myOriginalList = list;
        myFilteredList = new ArrayList<Event>();
        if (myDecoratedFilter == null)
        {
            return list;
        }
        else
        {
            myDecoratedFilter.filter(list);
            return myDecoratedFilter.getFilteredList();
        }
    }


    public void appendFilter (Filter filter)
    {
        myDecoratedFilter = filter;
    }

}
