package event;

import java.util.Date;
import java.util.HashMap;

public class Event {
    
    private HashMap<Type,Object> myField = new HashMap<Type,Object>();
    public static enum Type {TITLE, SUMMARY, URL, START, END}; 

    public Event (HashMap<Type,Object> map) {
        myField = map; 
    }


    public Object get(Type key) {
        return myField.get(key);
    }

    public boolean containsKeywordInTitle(String keyword) {
        return ((String)myField.get(Type.TITLE)).contains(keyword);
    }

    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
        Date eventStartTime=(Date) myField.get(Type.START);
        Date eventEndTime= (Date) myField.get(Type.END);
        return (eventStartTime.before(endTime) && eventEndTime.after(startTime));
    }

}
