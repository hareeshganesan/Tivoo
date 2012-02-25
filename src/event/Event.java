package event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.text.ParseException;
import exception.TivooEventKeywordNotFound;

public class Event implements Comparable{
    
    private HashMap<String,String> myFields = new HashMap<String,String>();
    
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    
    public Event (HashMap<String,String> map) {
        myFields = map; 
    }

    public String get(String key) {
        String toReturn = myFields.get(key); 
        if (toReturn == null) {
            throw new TivooEventKeywordNotFound(key);
        }
        else {
            return toReturn;
        }
    }

    public boolean containsKeyWord(String key, String keyword) {
        return get(key).contains(keyword);
    }
    
    public boolean containsKeyWord(String keyword) {
        for (String s:myFields.values()) {
            if (s.contains(keyword)) {
                return true;
            }
        }
        return false;
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
