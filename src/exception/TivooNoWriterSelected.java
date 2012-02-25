package exception;

@SuppressWarnings("serial")
public class TivooNoWriterSelected extends TivooException
{

    public TivooNoWriterSelected ()
    {
        this("");
    }


    public TivooNoWriterSelected (String message)
    {
        super(message);
    }
}
