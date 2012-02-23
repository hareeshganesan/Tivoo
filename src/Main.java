import java.io.File;

import exception.TivooException;



public class Main {
    public static void main (String[] args) {    
        TivooSystem model = new TivooSystem();
        try {
            File file = new File("./xml/dukecal.xml");
//            File file = new File("./xml/DukeBasketBall.xml");
            model.loadFile(file);
            model.addFilterByKeyword("Exhibition");
//            model.addFilterByKeyword("vs");
//            model.addFilterByTimeFrame("11/21/11 6:30:00 PM", "01/21/12 1:00:00 PM");        
            model.addSummaryAndDetailPagesWriter("./html/summary.html");
            model.perform();
        } catch (TivooException e) {
            System.out.println(e.getMessage());
        }
    }
}
