package exception;

@SuppressWarnings("serial")
public class TivooEventKeywordNotFound extends TivooException
{

    public TivooEventKeywordNotFound ()
    {
        this("");
    }


    public TivooEventKeywordNotFound (String message)
    {
        super(message);
    }

}
