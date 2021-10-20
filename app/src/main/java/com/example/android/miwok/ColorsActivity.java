package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "Erupu", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("blue", "Nilam", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("yellow", "Pasupu", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "Akupacca", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("pink", "Gulabi", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("orange", "Narinja", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "Nalupu", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "Telupu", R.drawable.color_white, R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currWord = words.get(position);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, currWord.getmMediaResourceId());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                        mediaPlayer = null;
//                        Toast.makeText(ColorsActivity.this, "Memory released!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mediaPlayer.stop();
        mediaPlayer.release();
        finish();
    }
}