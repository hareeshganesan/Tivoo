package exception;

@SuppressWarnings("serial")
public abstract class TivooException extends RuntimeException{

    public TivooException(String message) {
        super(message);
    }

    
//    public static enum Type { UNRECOGNIZED_FEED, INVALID_FEED, INTERNAL_PARSING_ERROR, BAD_DATEFORMAT };
//    private Type myType;
    
//    public TivooException (Type type) {
//        this("", type);
//    }
    
//    public TivooException (String message, Type type) {
//        super(message);
//        myType = type;
//    }
    
//    public Type getType()
//    {
//        return myType;
//    }
	
}
