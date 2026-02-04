/*
 * CMPUT 301 - Software Engineering
 * Assignment 1: EmotiLog - A Quick Emotion Logging App
 * 
 * Class: MainActivity
 * Purpose: Main entry point of the application. Displays emoticon buttons for logging emotions,
 *          manages the emotion log, and provides navigation to Events List and Summary views.
 * 
 * Design Rationale:
 * - Central hub for emotion logging with 6 preset emoticon buttons
 * - Maintains ArrayList of EmotionEntry objects for session persistence
 * - Uses ArrayAdapter for potential ListView integration
 * - Provides navigation to detailed events list and summary statistics
 * 
 * Outstanding Issues:
 * - ListView not currently displayed; entries added but not shown on main screen
 */

package com.example.dagman_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<EmotionEntry> emotionLog;
    private ArrayAdapter<EmotionEntry> emotionAdapter;
    private Button button3, button4, button5, buttonWinking, buttonAngry, buttonInLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emotionLog = new ArrayList<>();

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        buttonWinking = findViewById(R.id.buttonWinking);
        buttonAngry = findViewById(R.id.buttonAngry);
        buttonInLove = findViewById(R.id.button2);

        emotionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emotionLog);

        Button eventsButton = findViewById(R.id.events_button);
        if (eventsButton != null) {
            eventsButton.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Events List clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EventsListActivity.class);
                intent.putParcelableArrayListExtra("emotionLog", emotionLog);
                startActivity(intent);
            });
        }

        Button summaryButton = findViewById(R.id.summary_button);
        if (summaryButton != null) {
            summaryButton.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Summary clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                intent.putParcelableArrayListExtra("emotionLog", emotionLog);
                startActivity(intent);
            });
        }

        if (button3 != null) button3.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Neutral clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("Neutral");
        });
        
        if (button4 != null) button4.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Happy clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("Happy");
        });
        
        if (button5 != null) button5.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Sad clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("Sad");
        });
        
        if (buttonWinking != null) buttonWinking.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Winking clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("Winking");
        });
        
        if (buttonAngry != null) buttonAngry.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Angry clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("Angry");
        });
        
        if (buttonInLove != null) buttonInLove.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "In Love clicked", Toast.LENGTH_SHORT).show();
            addEmotionEntry("In Love");
        });
    }

    private void addEmotionEntry(String emotion) {
        String timestamp = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        EmotionEntry entry = new EmotionEntry(emotion, timestamp);
        emotionLog.add(0, entry);
        emotionAdapter.notifyDataSetChanged();
    }
}