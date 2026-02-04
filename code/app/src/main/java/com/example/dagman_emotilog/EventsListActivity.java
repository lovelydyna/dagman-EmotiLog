/*
 * CMPUT 301 - Software Engineering
 * Assignment 1: EmotiLog - A Quick Emotion Logging App
 * 
 * Class: EventsListActivity
 * Purpose: Displays a detailed list of all logged emotion entries with timestamps.
 *          Allows users to review the complete chronological (reverse) history of logged emotions.
 * 
 * Design Rationale:
 * - Receives emotion log via Intent as Parcelable ArrayList
 * - Uses ArrayAdapter to display EmotionEntry objects in a ListView
 * - Provides back navigation to return to main activity
 */

package com.example.dagman_emotilog;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventsListActivity extends AppCompatActivity {

    /**
     * Activity creation lifecycle method.
     * Receives emotion log from MainActivity and displays it in a ListView.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list);

        // Retrieve emotion log from Intent (passed from MainActivity as Parcelable)
        ArrayList<EmotionEntry> emotionLog = getIntent().getParcelableArrayListExtra("emotionLog");

        // Get reference to the ListView for displaying emotion entries
        ListView eventsList = findViewById(R.id.events_full_list);
        
        // Populate ListView with emotion entries if data is available
        if (emotionLog != null && eventsList != null) {
            // Create adapter to bind emotion entries to list view items
            ArrayAdapter<EmotionEntry> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emotionLog);
            eventsList.setAdapter(adapter);
        }

        // Set up back button to return to MainActivity
        Button backButton = findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }
    }
}
