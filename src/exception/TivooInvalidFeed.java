package exception;

@SuppressWarnings("serial")
public class TivooInvalidFeed extends TivooException
{

    public TivooInvalidFeed ()
    {
        this("");
    }


    public TivooInvalidFeed (String message)
    {
        super(message);
    }

}
