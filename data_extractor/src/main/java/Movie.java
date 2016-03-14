/**
 * Created by skim8 on 3/13/2016.
 */
public class Movie {
    private String title;
    private long budget;
    private long gross;
    private long year;

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
}
