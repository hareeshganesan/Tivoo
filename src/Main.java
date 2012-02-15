
public class Main {
    public static void main (String[] args) {        
        TivooSystem s = new TivooSystem();
        s.loadFile("./dukecal.xml");
        s.filterByKeyword("Exhibition");
        s.filterByTimeFrame("11/21/11 6:30 PM", "01/21/12 1:00 PM");        
        s.outputSummary("./html/summary.html");
        s.outputDetails("./html/details.html");
    }

}
