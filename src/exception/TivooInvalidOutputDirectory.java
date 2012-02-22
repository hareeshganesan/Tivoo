package exception;

@SuppressWarnings("serial")
public class TivooInvalidOutputDirectory extends TivooException{

    public TivooInvalidOutputDirectory() {
        this("");
    }
    
    public TivooInvalidOutputDirectory (String message) {
        super(message);
    }

}
