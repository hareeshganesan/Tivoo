package writer;

import java.util.List;
import event.Event;


public class DumbWriter extends Writer
{
    public DumbWriter (String filename)
    {
        setMyDirectory(filename);

    }


    @Override
    public void outputHTML (List<Event> events)
    {

        StringBuilder sb = new StringBuilder();

        sb.append("<html>\n<body>\n");
        sb.append("<h1> Dumb View Just Shows Titles </h1>");
        sb.append("\n");
        sb.append("<p>");
        for (Event event : events)
        {
            sb.append("<p>");
            String title = event.get("title");
            sb.append(title);
            sb.append("</p>");
        }
        System.out.println(getMyDirectory());
        write(sb.toString(), getMyDirectory());
    }

}
