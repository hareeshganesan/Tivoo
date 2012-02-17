package exception;

@SuppressWarnings("serial")
public class TivooException extends RuntimeException{

    public static enum Type { BAD_INPUTDIRECTORY, BAD_OUTPUTDIRECTORY, BAD_DATEFORMAT, BAD_PARSING };
    private Type myType;
    
    public TivooException (Type type) {
        this("", type);
    }
    
    public TivooException (String message, Type type) {
        super(message);
        myType = type;
    }
    
    public Type getType()
    {
        return myType;
    }
	
}
