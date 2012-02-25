package exception;

@SuppressWarnings("serial")
public class TivooNoFilterSelected extends TivooException
{

    public TivooNoFilterSelected ()
    {
        this("");
    }


    public TivooNoFilterSelected (String message)
    {
        super(message);
    }
}
