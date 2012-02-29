package exception;

@SuppressWarnings("serial")
public class TivooEventKeywordNotFound extends TivooSystemError
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
