package event;

import java.util.HashMap;

public class Event {
    
    private HashMap<String,String> myField = new HashMap<String,String>();

    public Event (HashMap<String,String> map) {
        myField = map; 
    }


    public String get(String key) {
        /**
         * TODO: throw exceptions for strings that do not exist
         */
        return myField.get(key);
    }

    public boolean containsKeyWord(String key, String keyword){
        return ((String)myField.get(key)).contains(keyword);
    }

}
