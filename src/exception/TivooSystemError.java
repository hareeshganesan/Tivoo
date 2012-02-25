package exception;

@SuppressWarnings("serial")
public class TivooSystemError extends TivooException
{

    public TivooSystemError ()
    {
        this("");
    }


    public TivooSystemError (String message)
    {
        super(message);
    }

}
