import exception.TivooException;


public class Main {
    public static void main (String[] args) {    
        TivooSystem s = new TivooSystem();
        try {
            s.loadFile("./somewhere/DukeBasketBal.xml");
            s.filterByKeyword("vs");
//            s.loadFile("./dukecal.xml");
//            s.filterByKeyword("Exhibition");
            s.filterByTimeFrame("11/21/11 6:30 PM", "01/21/12 1:00 PM");        
            s.outputSummary("./somewhere/summary.html");
            s.outputDetails("./somewhere/event");
        } catch (TivooException e) {
            System.out.println(e.getMessage());
        }
    }

}
