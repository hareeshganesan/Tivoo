package event;

import java.util.Date;


public class Event
{
    private String myTitle;
    private String mySummary;
    private Date myStart;
    private Date myEnd;


    public Event (String title, String sum, Date start, Date end)
    {
        myTitle = title;
        mySummary = sum;
        myStart = start;
        myEnd = end;
    }


    public String getMyTitle ()
    {
        return myTitle;
    }


    public String getMySummary ()
    {
        return mySummary;
    }


    public Date getMyStart ()
    {
        return myStart;
    }


    public Date getMyEnd ()
    {
        return myEnd;
    }
    
    public boolean containsKeywordInTitle(String keyword) {
        return myTitle.contains(keyword);
    }
    
    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
        return (myStart.before(endTime) && myEnd.after(startTime));
    }

}
