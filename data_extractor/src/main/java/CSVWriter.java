import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by skim8 on 3/13/2016.
 */
public class CSVWriter {



    public void write(String filename, Map<String, Movie> map){




        FileWriter fw = null;

        try {
            fw = new FileWriter(filename);
            fw.append("title,year,budget,gross\n");
            for (String s: map.keySet()){
                Movie m = map.get(s);

                fw.append("\"" + m.getTitle() + "\"");
                fw.append(',');
                fw.append("\"" + String.valueOf(m.getYear()) + "\"");
                fw.append(',');
                fw.append("\"" + String.valueOf(m.getBudget()) + "\"");
                fw.append(',');
                fw.append("\"" + String.valueOf(m.getGross()) + "\"");
                fw.append('\n');

            }
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
