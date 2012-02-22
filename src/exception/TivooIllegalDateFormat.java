package exception;

@SuppressWarnings("serial")
public class TivooIllegalDateFormat extends TivooException{

    public TivooIllegalDateFormat() {
        this("");
    }
    
    public TivooIllegalDateFormat (String message) {
        super(message);
    }

}
