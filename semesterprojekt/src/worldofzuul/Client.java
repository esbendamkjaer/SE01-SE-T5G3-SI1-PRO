package worldofzuul;

import java.net.http.HttpClient;
import java.time.Duration;

public class Client {

    private HttpClient httpClient;

    public Client(int timeout) {

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
    public void sendScoreData(ScoreData scoreData) {
        throw new UnsupportedOperationException();
    }

}
