import filter.Filter;
import parser.Parser;
import writer.Writer;

public class Main
{
    public static void main(String[] args){
        Parser parser = new Parser("dukecal.xml");
        (new SummaryWriter()).write((new Filter()).filter(parser.parse()));
        
    }
}
