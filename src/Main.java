

public class Main {
    public static void main (String[] args) {    
        TivooSystem model = new TivooSystem();
        TivooViewer view = new TivooViewer(model);
        view.setVisible(true);
//        try {
//            s.loadFile("./xml/DukeBasketBall.xml");
//            s.filterByKeyword("vs");
//            s.filterByTimeFrame("11/21/11 6:30:00 PM", "01/21/12 1:00:00 PM");        
//            s.outputSummary("./html/summary.html");
//            s.outputDetails("./html/detail");
//        } catch (TivooException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
