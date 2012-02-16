package event;

import java.util.Date;


public class Event
{
    private String myTitle;
    private String mySummary;
    private Date myStart;
    private Date myEnd;
    private String myUrl;


    public Event (String title, String sum, Date start, Date end, String url)
    {
        myTitle = title;
        mySummary = sum;
        myStart = start;
        myEnd = end;
        myUrl = url; 
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
    
    public String getMyUrl() {
        return myUrl;
    }
    
    public boolean containsKeywordInTitle(String keyword) {
        return myTitle.contains(keyword);
    }
    
    public boolean isWithinTimeFrame(Date startTime, Date endTime) {
        return (myStart.before(endTime) && myEnd.after(startTime));
    }
    
    public String toString ()
    {
        String ret = "";
        ret += "Title: " + myTitle;
        ret += "\nStart Date: " + myStart.toString();
        ret += "\nEnd Date: " + myEnd.toString();
        ret += "\nSummary: " + mySummary;

        return ret;
    }
    

}
