package dk.sdu.worldoftrash.game.data;

import com.google.gson.Gson;
import dk.sdu.worldoftrash.game.domain.scoresystem.ScoreData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Client implements IDataAccess {

    private HttpClient httpClient;
    private String url;
    private int timeout;

    public Client(int timeout, String url) {
        this.url = url;
        this.timeout = timeout;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(timeout))
                .build();
    }

    /**
     * Send a given ScoreData object to the web server.
     * @param scoreData ScoreData object to send.
     */
    @Override
    public void saveData(ScoreData scoreData) {
        Gson serializer = new Gson();

        String data;
        data = serializer.toJson(scoreData);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url + "/createScoreData"))
                .timeout(Duration.ofSeconds(timeout))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        httpClient.sendAsync(postRequest, HttpResponse.BodyHandlers.ofString()).thenApply(stringHttpResponse -> {
            if (stringHttpResponse.statusCode() == 429) {
                System.out.println("You are trying to save too frequently.");
            }
            return stringHttpResponse;
        });
    }
}
