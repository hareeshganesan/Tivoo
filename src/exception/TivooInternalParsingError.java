package exception;

@SuppressWarnings("serial")
public class TivooInternalParsingError extends TivooException{

    public TivooInternalParsingError() {
        this("");
    }
    
    public TivooInternalParsingError (String message) {
        super(message);
    }

}
