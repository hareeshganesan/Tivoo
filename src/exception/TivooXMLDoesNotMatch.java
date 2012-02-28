package exception;

@SuppressWarnings("serial")
public class TivooXMLDoesNotMatch extends TivooException
{    
    public TivooXMLDoesNotMatch ()
    {
        this("");
    }


    public TivooXMLDoesNotMatch (String message)
    {
        super(message);
    }

}
