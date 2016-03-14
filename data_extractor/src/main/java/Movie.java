/**
 * Created by skim8 on 3/13/2016.
 */
public class Movie {
    private int id;
    private String title;
    private long budget;
    private long gross;
    private long year;
    private String rated;
    private String releasedMonth;
    private int runtime;
    private String genre;
    private String director;
    private String writer;
    private String mainActor;
    private String language;
    private String rating;
    private String votes;
    private String metascore;

    public Movie(String title, long budget, long gross, long year) {
        this.title = title;
        this.budget = budget;
        this.gross = gross;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleasedMonth() {
        return releasedMonth;
    }

    public void setReleasedMonth(String releasedMonth) {
        this.releasedMonth = releasedMonth;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", budget=" + budget +
                ", gross=" + gross +
                ", year=" + year +
                ", rated='" + rated + '\'' +
                ", releasedMonth='" + releasedMonth + '\'' +
                ", runtime=" + runtime +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", language='" + language + '\'' +
                ", rating='" + rating + '\'' +
                ", votes=" + votes +
                ", metascore='" + metascore + '\'' +
                '}';
    }
}
