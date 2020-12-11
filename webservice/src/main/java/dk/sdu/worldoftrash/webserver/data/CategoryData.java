package dk.sdu.worldoftrash.webserver.data;

public class CategoryData {

    private int correct;
    private int total;

    public CategoryData(int correct, int total) {
        this.correct = correct;
        this.total = total;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
