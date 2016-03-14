import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by skim8 on 3/13/2016.
 */
public class App {


    public static void main(String[] args) throws URISyntaxException {

        String filename = "data/business.list";
        String output = "movies.csv";

        File file = Paths.get(ClassLoader.getSystemResource(filename).toURI()).toFile();

        BusinessParser bp = new BusinessParser(file);

        System.out.println("Parsing datafile: " + filename);
        Map<String, Movie> map = bp.run();


        System.out.println("Writing to file: " + output);
        CSVWriter cw = new CSVWriter();

        cw.write(output, map);


    }
}
