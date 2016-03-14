import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by skim8 on 3/14/2016.
 */
public class OmdbParser {
    Map<String, Movie> map;
    int count;

    public OmdbParser(Map<String, Movie> map) {
        this.map = map;
        count = 0;
    }

    public Map<String, Movie> getDataFromOmdbApi() {

        URL url = null;

        for (String title : map.keySet()) {

            String urlString = "http://www.omdbapi.com/?t=" + title.replaceAll(" ", "+") + "&y=&plot=short&r=json";
            try {
                url = new URL(urlString);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String line = br.readLine();

                parseMovie(line);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return clearMap(map);
    }


    private void parseMovie(String line) {
        JSONParser jp = new JSONParser();
        count++;
        try {
            JSONObject json = (JSONObject) jp.parse(line);

            if (json.keySet().contains("Title")) {
                System.out.println(count + ": " + json);
                String title = (String) json.get("Title");
                Movie movie = map.get(title);
                String runtime = (String) json.get("Runtime");
                String rated = (String) json.get("Rated");
                String director = (String) json.get("Director");

                if (movie != null && checkValidity(rated, runtime, director)) {
                    String released = (String) json.get("Released");
                    String genre = (String) json.get("Genre");
                    String writer = (String) json.get("Writer");
                    String actors = (String) json.get("Actors");
                    String language = (String) json.get("Language");
                    String metascore = (String) json.get("Metascore");
                    String rating = (String) json.get("imdbRating");
                    String votes = (String) json.get("imdbVotes");

                    movie.setDirector(director);
                    movie.setRated(rated);
                    movie.setGenre(genre.split(",")[0]);
                    movie.setReleasedMonth(parseReleasedMonth(released));
                    movie.setRuntime(Integer.parseInt(runtime.split(" ")[0]));
                    movie.setWriter(parseWriter(writer, director));
                    movie.setMainActor(actors.split(",")[0]);
                    movie.setLanguage(language.split(",")[0]);
                    movie.setMetascore(metascore);
                    movie.setVotes(votes);
                    movie.setRating(convertRating(rating));

                    map.put(title, movie);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    private String parseDirector(String director){
        if (containsNA(director)) return director;
        String[] array = director.split(",");
        return array[0].trim();
    }

    private String parseReleasedMonth(String released){
        if (containsNA(released)) return released;
        String[] array = released.split(" ");
        return array[1].trim();
    }
    private boolean checkValidity(String rated, String runtime, String director) {
        if (containsNA(rated) || containsNA(runtime) || containsNA(director)) return false;
        return true;
    }

    private boolean containsNA(String str) {
        if (str.contains("N/A")) return true;
        if (str.contains("N\\/A")) return true;
        return false;
    }

    private String convertRating(String rating) {
        if (containsNA(rating)) return rating;
        float f = Float.parseFloat(rating);
        return String.valueOf(Math.round(f * 10));
    }

    private String parseWriter(String writerStr, String director) {
        if (containsNA(writerStr)) return director;
        String[] writers = writerStr.split(",");
        String writer = writers[0];
        writer = writer.replaceAll("\\(.+\\)", "").trim();
        return writer;
    }

    private Map<String, Movie> clearMap(Map<String, Movie> map){
        Map<String, Movie> newMap = new HashMap<>();

        for (String title: map.keySet()){
            Movie m = map.get(title);
            if (m.getDirector() != null && m.getRuntime() != 0 && m.getMainActor() != null) {
                newMap.put(title, m);
            }
        }
        return newMap;
    }

}


