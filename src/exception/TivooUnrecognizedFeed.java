package exception;

@SuppressWarnings("serial")
public class TivooUnrecognizedFeed extends TivooException
{

    public TivooUnrecognizedFeed ()
    {
        this("");
    }


    public TivooUnrecognizedFeed (String message)
    {
        super(message);
    }
}
