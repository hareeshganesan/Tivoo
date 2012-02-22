package event;

import java.util.Date;
import java.util.HashMap;

public class Event {
    /**
     * TODO: expose the keyset
     */
    
    private HashMap<String,String> myField = new HashMap<String,String>();

    public Event (HashMap<String,String> map) {
        myField = map; 
    }


    public Object get(String key) {
        return myField.get(key);
    }

    /**
     * TODO: make the methods more generic across keys
     * @param keyword
     * @return
     */
    public boolean containsKeywordInTitle(String keyword) {
        return ((String)myField.get(Type.TITLE)).contains(keyword);
    }

    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
        Date eventStartTime=(Date) myField.get(Type.START);
        Date eventEndTime= (Date) myField.get(Type.END);
        return (eventStartTime.before(endTime) && eventEndTime.after(startTime));
    }

}
