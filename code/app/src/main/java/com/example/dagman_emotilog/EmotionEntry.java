/*
 * CMPUT 301 - Software Engineering
 * Assignment 1: EmotiLog - A Quick Emotion Logging App
 * 
 * Class: EmotionEntry
 * Purpose: Represents a single emotion log entry with an emotion type and timestamp.
 *          Implements Parcelable for inter-activity data passing in Android.
 * 
 * Design Rationale:
 * - Implements Parcelable to efficiently pass data between Android Activities
 * - Immutable fields (emotion, timestamp) ensure data integrity once created
 * - Parcelable implementation allows ArrayList serialization for Intent extras
 */

package com.example.dagman_emotilog;

import android.os.Parcel;
import android.os.Parcelable;

public class EmotionEntry implements Parcelable {
    private String emotion;
    private String timestamp;

    public EmotionEntry(String emotion, String timestamp) {
        this.emotion = emotion;
        this.timestamp = timestamp;
    }

    protected EmotionEntry(Parcel in) {
        emotion = in.readString();
        timestamp = in.readString();
    }

    public static final Creator<EmotionEntry> CREATOR = new Creator<EmotionEntry>() {
        @Override
        public EmotionEntry createFromParcel(Parcel in) {
            return new EmotionEntry(in);
        }

        @Override
        public EmotionEntry[] newArray(int size) {
            return new EmotionEntry[size];
        }
    };

    public String getEmotion() {
        return emotion;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return emotion + " - " + timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(emotion);
        dest.writeString(timestamp);
    }
}