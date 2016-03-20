import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by skim8 on 3/14/2016.
 */
public class practice {

    public static void main(String[] args) throws ParseException {

        String line = "{\"Released\":\"18 Mar 2011\",\"Metascore\":\"N\\/A\",\"imdbID\":\"tt1857717\",\"Plot\":\"N\\/A\",\"Director\":\"David Skato\",\"Title\":\"Gutta Story\",\"Actors\":\"Doughbelly Stray, Shunn Bennett, Dirty Red\",\"imdbRating\":\"N\\/A\",\"imdbVotes\":\"N\\/A\",\"Response\":\"True\",\"Runtime\":\"N\\/A\",\"Type\":\"movie\",\"Awards\":\"N\\/A\",\"Year\":\"2011\",\"Language\":\"English\",\"Rated\":\"N\\/A\",\"Poster\":\"N\\/A\",\"Country\":\"USA\",\"Genre\":\"Crime\",\"Writer\":\"David Skato\"}";
        Movie movie = new Movie("Kung fu", 1, 1, 1);

        JSONParser jp = new JSONParser();

        JSONObject json = (JSONObject) jp.parse(line);

        String title = (String) json.get("Title");
        String rated = (String) json.get("Rated");
        String released = (String) json.get("Released");
        String runtime = (String) json.get("Runtime");
        String genre = (String) json.get("Genre");
        String director = (String) json.get("Director");
        String writer = (String) json.get("Writer");
        String actors = (String) json.get("Actors");
        String language = (String) json.get("Language");
        String metascore = (String) json.get("Metascore");
        String rating = (String) json.get("imdbRating");
        String votes = (String) json.get("imdbVotes");

        System.out.println(checkValidity(rated, runtime));

        movie.setDirector(director);
        movie.setRated(rated);
        movie.setRated(genre);
        movie.setReleasedMonth(released.split(" ")[1]);
        movie.setRuntime(Integer.parseInt(runtime.split(" ")[0]));
        movie.setWriter(parseWriter(writer));
        movie.setMainActor(actors.split(",")[0]);
        movie.setLanguage(language.split(",")[0]);
        movie.setMetascore(metascore);
        movie.setVotes(votes);
        movie.setRating(rating);

        System.out.println(movie);

    }

    private static int convertRating(String rating) {
        if (rating.isEmpty()) return -1;
        return Integer.parseInt(rating.replaceAll(".", ""));
    }

    private static String parseWriter(String writerStr) {
        String[] writers = writerStr.split(",");
        String writer = writers[0];
        writer = writer.replaceAll("\\(.+\\)", "").trim();
        return writer;
    }

    private static boolean checkValidity(String rated, String runtime){
        System.out.println("rated: " + rated + ", runtime: " + runtime);

        int count = 0;
        if (containsNA(rated)) return false;
        if (containsNA(runtime)) return false;
        return true;
    }

    private static boolean containsNA(String str){
        if (str.contains("N/A")) return true;
        if (str.contains("N\\/A")) return true;
        return false;
    }


}
