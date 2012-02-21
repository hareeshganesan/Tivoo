package event;

import java.util.Date;
import java.util.HashMap;


public class Event
{
   private HashMap<String,Object> myField =new HashMap<String,Object>();


    public Event (HashMap<String,Object> map)
    {
           myField.putAll(map); 
    }


    public Object get(String key){
    	return myField.get(key);
    }
    
    public boolean containsKeyword(String title,String keyword) {
        return ((String)myField.get(title)).contains(keyword);
    }
    
    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
    	Date eventStartTime=(Date) myField.get("startTime");
    	Date eventEndTime= (Date) myField.get("endTime");
        return (eventStartTime.before(endTime) && eventEndTime.after(startTime));
    }
    
 

}
