package event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.text.ParseException;


public class Event implements Comparable
{

    private HashMap<String, String> myFields = new HashMap<String, String>();

    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";


    public Event (HashMap<String, String> map)
    {
        myFields = map;
    }


    public String get (String key)
    {
        /**
         * TODO: throw exceptions for strings that do not exist
         */
        return myFields.get(key);
    }


    public boolean containsKeyWord (String key, String keyword)
    {
        return ((String) myFields.get(key)).contains(keyword);
    }


    public int compareTo (Object otherEvent)
    {
        Event other = (Event) otherEvent;
        DateFormat format = new SimpleDateFormat(dateFormat);
        int comparedValue = 0;
        try
        {
            comparedValue =
                format.parse(myFields.get("startTime"))
                      .compareTo(format.parse(other.get("startTime")));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return comparedValue;
    }

}
