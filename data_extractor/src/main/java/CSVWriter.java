import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by skim8 on 3/13/2016.
 */
public class CSVWriter {
    public void write(String filename, Map<String, Movie> map) {
        FileWriter fw = null;
        int id = 1;
        try {
            fw = new FileWriter(filename);
            fw.append("id,title,year,released_month,genre,rated,runtime,language,director,writer,main_actor,metascore,rating,votes,budget,gross\n");
            for (String s : map.keySet()) {
                Movie m = map.get(s);
                fw.append("\"" + id++ + "\"");
                fw.append(',');
                fw.append("\"" + m.getTitle() + "\"");
                fw.append(',');
                fw.append("\"" + m.getYear() + "\"");
                fw.append(',');
                fw.append("\"" + m.getReleasedMonth() + "\"");
                fw.append(',');
                fw.append("\"" + m.getGenre() + "\"");
                fw.append(',');
                fw.append("\"" + m.getRated() + "\"");
                fw.append(',');
                fw.append("\"" + m.getRuntime() + "\"");
                fw.append(',');
                fw.append("\"" + m.getLanguage() + "\"");
                fw.append(',');
                fw.append("\"" + m.getDirector() + "\"");
                fw.append(',');
                fw.append("\"" + m.getWriter() + "\"");
                fw.append(',');
                fw.append("\"" + m.getMainActor() + "\"");
                fw.append(',');
                fw.append("\"" + m.getMetascore() + "\"");
                fw.append(',');
                fw.append("\"" + m.getRating() + "\"");
                fw.append(',');
                fw.append("\"" + m.getVotes() + "\"");
                fw.append(',');
                fw.append("\"" + m.getBudget() + "\"");
                fw.append(',');
                fw.append("\"" + m.getGross() + "\"");
                fw.append('\n');

            }
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
