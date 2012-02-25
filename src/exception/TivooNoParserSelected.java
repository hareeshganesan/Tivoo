package exception;

@SuppressWarnings("serial")
public class TivooNoParserSelected extends TivooException
{

    public TivooNoParserSelected ()
    {
        this("");
    }


    public TivooNoParserSelected (String message)
    {
        super(message);
    }
}
