package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "Mīru ekkaḍiki veḷutunnāru?",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "Nī pēru ēmiṭi?",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "Nā pēru...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "Nī anubhūti elā undi?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "Nenu bagunnanu",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "Mīru vastunnārā?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "Avunu, nēnu vastunnānu.",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "Nēnu vastunnānu.",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "Veḷdāṁ.",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "Ikkaḍiki raṇḍi.",R.raw.phrase_come_here));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currWord = words.get(position);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, currWord.getmMediaResourceId());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                        mediaPlayer = null;
//                        Toast.makeText(PhrasesActivity.this, "Memory released!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}