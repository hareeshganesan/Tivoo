import java.io.File;
import exception.TivooException;


public class Main
{
    public static void main (String[] args)
    {
        TivooSystem model = new TivooSystem();
        try
        {

//            File file1 = new File("./xml/dukecal.xml");
            File file2 = new File("./xml/DukeBasketBall.xml");
//            File file3 = new File("./xml/TVTest.xml");

//            model.loadFile(file3);
            model.loadFile(file2);
            model.addFilterByKeyword("vs");
//            model.addFilterByTimeFrame("11/21/11 6:30:00 PM", "01/21/12 1:00:00 PM"); 

//            model.addFilterByKeywordInGeneral("Duke");
//
//            model.addFilterByKeywordSorting("title");

            model.addSummaryAndDetailPagesWriter("./html/summary.html");

            model.addConflictWriter("./html/conflicts.html");

            model.addListWriter("./html/listview.html");

            model.addCalendarWriter("./html/calendarview.html", "2011-11-21 18:30:00", "MONTH");

            model.perform();


        }
        catch (TivooException e)
        {

            System.out.println(e.getMessage());
        }
    }
}
