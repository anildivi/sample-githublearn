package com.snake.controller;

import com.snake.model.HighScore;
import com.snake.repository.HighScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app
public class HighScoreController {

    @Autowired
    private HighScoreRepository highScoreRepository;

    @GetMapping
    public List<HighScore> getTopScores() {
        return highScoreRepository.findTop10ByOrderByScoreDesc();
    }

    @PostMapping
    public HighScore addScore(@RequestBody HighScore highScore) {
        // Reset date to server time to prevent tampering
        highScore.setDate(java.time.LocalDateTime.now());
        return highScoreRepository.save(highScore);
    }
}
