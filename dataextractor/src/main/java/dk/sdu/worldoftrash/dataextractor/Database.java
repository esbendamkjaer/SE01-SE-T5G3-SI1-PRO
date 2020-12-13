package dk.sdu.worldoftrash.dataextractor;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import dk.sdu.worldoftrash.dataextractor.data.CategoryData;
import dk.sdu.worldoftrash.dataextractor.data.LevelData;
import dk.sdu.worldoftrash.dataextractor.data.ScoreData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Database {

    public static final String COL_NAME = "scores";

    public Database() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("/serviceAccountKey.json")))
                    .setDatabaseUrl("https://semesterprojekt1-ce955.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSV(String file) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterator<DocumentReference> iterator = dbFirestore.collection(COL_NAME).listDocuments().iterator();

        String[] headers = {"levelName", "correct", "score"};

        FileWriter out = null;
        CSVPrinter printer = null;
        try {
            out = new FileWriter(file);
            printer = new CSVPrinter(out, CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withDelimiter(';'));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (iterator.hasNext()) {
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();

            ScoreData scoreData = documentSnapshot.toObject(ScoreData.class);

            //System.out.println(scoreData.getUuid());

            if (scoreData.getLevels().size() < 3) continue;

            for (Map.Entry<String, LevelData> levelEntry : scoreData.getLevels().entrySet()) {
                String levelName = levelEntry.getKey();
                LevelData levelData = levelEntry.getValue();

                int correctSum = 0;
                int totalSum = 0;
                for (Map.Entry<String, CategoryData> categoryEntry : levelData.getCorrectlySortedByWasteType().entrySet()) {
                    String categoryName = categoryEntry.getKey();
                    CategoryData categoryData = categoryEntry.getValue();

                    correctSum += categoryData.getCorrect();
                    totalSum += categoryData.getTotal();
                }

                try {
                    int score = 0;
                    if (levelName.equals("supermarket")) {
                        score = levelData.getScore();
                        if (correctSum == 12) {
                            System.out.println(scoreData.getUuid());
                        }
                    } else if (levelName.equals("hospital-outside")) {
                        score = levelData.getScore() - scoreData.getLevelDataByName("supermarket").getScore();
                    } else if (levelName.equals("school-outside")) {
                        score = levelData.getScore() - scoreData.getLevelDataByName("hospital-outside").getScore();
                    }
                    printer.printRecord(levelName, correctSum, score);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //System.out.println("Correct in: " + levelName + ": " + correctSum + " out of " + totalSum);
            }
        }

        try {
            out.close();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            FirestoreClient.getFirestore().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
