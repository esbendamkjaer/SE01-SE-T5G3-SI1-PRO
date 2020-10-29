package dk.sdu.worldoftrash.webserver;

import dk.sdu.worldoftrash.shared.ScoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/createScoreData")
    public String createScoreData(@RequestBody ScoreData scoreData) throws ExecutionException, InterruptedException {
        return scoreService.saveScoreData(scoreData);
    }
}