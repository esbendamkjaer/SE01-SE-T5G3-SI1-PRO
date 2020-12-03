package dk.sdu.worldoftrash.dataextractor;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
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

        String[] headers = {"levelName", "correct"};

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

            System.out.println(scoreData.getUuid());

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
                    printer.printRecord(levelName, correctSum);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Correct in: " + levelName + ": " + correctSum + " out of " + totalSum);
            }
        }

        try {
            out.close();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScoreData getPatientDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        ScoreData scoreData = null;

        if(document.exists()) {
            scoreData = document.toObject(ScoreData.class);
            return scoreData;
        }else {
            return null;
        }
    }

    public String updatePatientDetails(ScoreData scoreData) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(scoreData.getUuid()).set(scoreData);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletePatient(String uuid) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(uuid).delete();
        return "Document with UUID " + uuid + " has been deleted";
    }

}
