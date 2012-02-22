package event;

import java.util.HashMap;

public class Event {
    
    private HashMap<String,String> myFields = new HashMap<String,String>();
    
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    
    public Event (HashMap<String,String> map) {
        myFields = map; 
    }


    public String get(String key) {
        /**
         * TODO: throw exceptions for strings that do not exist
         */
        return myFields.get(key);
    }

    public boolean containsKeyWord(String key, String keyword){
        return ((String)myFields.get(key)).contains(keyword);
    }

}
