import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by skim8 on 3/13/2016.
 */
public class BusinessParser {
    private File file;
    private Map<String, Movie> map;

    public BusinessParser(File file) {
        this.file = file;
        map = new HashMap<>();
    }

    public Map<String, Movie> getMovieMap() {
        BufferedReader br = null;

        try {

            String line;

            br = new BufferedReader(new FileReader(file));

            boolean hasMV = false;
            boolean parsedGR = false;
            int year = -1;
            String title = "";
            long budget = -1;
            long gross = -1;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("MV")) {
                    if (!line.contains("TV")) {
                        year = parseYear(line);
                        if (year >= 2015) {
                            hasMV = true;
                            title = parseTitle(line);
                        }
                    }
                }

                if (hasMV && line.startsWith("BT") && line.contains("USD")) {
                    budget = parseBudget(line);

                }

                if (hasMV && !parsedGR && line.startsWith("GR") && line.contains("USA")) {
                    gross = parseGross(line);

                    parsedGR = true;
                }

                if (hasMV && line.contains("------")) {

                    if (year != -1 && title.length() != 0 && budget != -1 && gross != -1) {
                        Movie movie = new Movie(title, budget, gross, year);
                        map.put(title, movie);
                    }
                    year = -1;
                    title = "";
                    budget = -1;
                    gross = -1;
                    hasMV = false;
                    parsedGR = false;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                br.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }

    }
    public void printMap(Map<String, Movie> map){
        for (String s: map.keySet()){
            System.out.println(map.get(s));
        }
    }

    private long parseGross(String line) {
        String [] grs = line.split(" ");
        String grossStr = grs[2];

        grossStr = grossStr.replaceAll(",", "");
        if (grossStr.length() > 14) return -1;

        return Long.parseLong(grossStr);
    }

    private long parseBudget(String line) {
        String[] bts = line.split(" ");
        String budgetStr = bts[2];

        budgetStr = budgetStr.replaceAll("," , "");
        if (budgetStr.length() > 14) return -1;

        return Long.parseLong(budgetStr);
    }

    public int parseYear(String line) {
        int index1 = line.indexOf('(') + 1;
        int index2 = line.indexOf(')', index1);
        String yearString = line.substring(index1, index2).replaceAll("\\D+", "");
        if (!yearString.trim().isEmpty()) return Integer.parseInt(yearString.trim());
        else return -1;
    }

    public String parseTitle(String line) {
        int colonIndex = line.indexOf(':');
        int parenIndex = line.indexOf('(', colonIndex);
        String title = line.substring(colonIndex + 1, parenIndex).trim();
        title = title.replaceAll("\"", "");
        return title;
    }


}
