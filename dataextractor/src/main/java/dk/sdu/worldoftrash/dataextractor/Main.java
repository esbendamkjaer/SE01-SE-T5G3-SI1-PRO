package dk.sdu.worldoftrash.dataextractor;

import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Database database = new Database();
        try {
            database.writeToCSV("data.csv");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        database.closeDBConnection();

    }

}
