package filter;

import java.util.List;
import event.Event;


public class FilterByKeywordInGeneral extends FilterDecorator
{

    private String myKeyword;


    public FilterByKeywordInGeneral (String keyword)
    {
        super();
        myKeyword = keyword;
    }


    @Override
    public void filter (List<Event> list)
    {
        List<Event> decoratedList = decoratedFilterWork(list);
        for (Event entry : decoratedList)
        {
            if (entry.containsKeyWord(myKeyword))
            {
                myFilteredList.add(entry);
            }
        }
    }
}
