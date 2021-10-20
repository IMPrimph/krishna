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

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "Nanna", R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "Amma", R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "Koduku", R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "Kuthuru", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "Annayya", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "Tammudu", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "Akka", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "Chelli", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "Nanamma", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "Tatayya", R.drawable.family_grandfather,R.raw.family_grandfather));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);

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
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, currWord.getmMediaResourceId());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                        mediaPlayer = null;
//                        Toast.makeText(FamilyActivity.this, "Memory released!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}