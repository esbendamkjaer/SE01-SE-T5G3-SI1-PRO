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
import dk.sdu.worldoftrash.dataextractor.data.ScoreData;
import dk.sdu.worldoftrash.dataextractor.data.WasteType;

import javax.swing.text.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
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

    public String savePatientDetails(ScoreData scoreData) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(scoreData.getUuid()).set(scoreData);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public void test() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterator<DocumentReference> iterator = dbFirestore.collection(COL_NAME).listDocuments().iterator();

        while (iterator.hasNext()) {
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();

            ScoreData scoreData = documentSnapshot.toObject(ScoreData.class);

            System.out.println(scoreData.getUuid());
            scoreData.getLevels().computeIfPresent("hospital-outside", (k, v) -> {

                v.getCorrectlySortedByWasteType().computeIfPresent(WasteType.RESIDUAL.toString(), (k1, v1) -> {
                    System.out.println(v1.getCorrect());
                    return v1;
                });

                return v;
            });
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
