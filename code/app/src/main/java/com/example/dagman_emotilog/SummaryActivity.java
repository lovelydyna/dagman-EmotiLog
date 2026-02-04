/*
 * CMPUT 301 - Software Engineering
 * Assignment 1: EmotiLog - A Quick Emotion Logging App
 * 
 * Class: SummaryActivity
 * Purpose: Displays statistical summary of logged emotions showing the count/frequency
 *          of each emotion type. Provides a quick overview of emotional trends.
 * 
 * Design Rationale:
 * - Receives emotion log via Intent as Parcelable ArrayList
 * - Counts occurrences of each emotion type using switch statement
 * - Displays counts in separate TextViews for each emotion category
 * - Supports 6 emotion types: Happy, Sad, Angry, Neutral, In Love, Winking
 */

package com.example.dagman_emotilog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_list);

        ArrayList<EmotionEntry> emotionLog = getIntent().getParcelableArrayListExtra("emotionLog");
        
        int happy = 0, sad = 0, angry = 0, neutral = 0, inLove = 0, winking = 0;
        
        if (emotionLog != null) {
            for (EmotionEntry entry : emotionLog) {
                switch (entry.getEmotion()) {
                    case "Happy":
                        happy++;
                        break;
                    case "Sad":
                        sad++;
                        break;
                    case "Angry":
                        angry++;
                        break;
                    case "Neutral":
                        neutral++;
                        break;
                    case "In Love":
                        inLove++;
                        break;
                    case "Winking":
                        winking++;
                        break;
                }
            }
        }

        TextView happyView = findViewById(R.id.summary_happy);
        TextView sadView = findViewById(R.id.summary_sad);
        TextView angryView = findViewById(R.id.summary_angry);
        TextView neutralView = findViewById(R.id.summary_neutral);
        TextView inloveView = findViewById(R.id.summary_inlove);
        TextView winkingView = findViewById(R.id.summary_winking);

        if (happyView != null) happyView.setText("Happy: " + happy);
        if (sadView != null) sadView.setText("Sad: " + sad);
        if (angryView != null) angryView.setText("Angry: " + angry);
        if (neutralView != null) neutralView.setText("Neutral: " + neutral);
        if (inloveView != null) inloveView.setText("In Love: " + inLove);
        if (winkingView != null) winkingView.setText("Winking: " + winking);

        Button backButton = findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }
    }
}
