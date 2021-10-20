package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //list of the numbers
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","Okati",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","Rendu",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","Moodu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","Nalugu",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","Eidhu",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","Aaru",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","Yedu",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","Enimidhi",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","Thommidhi",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","Padhi",R.drawable.number_ten,R.raw.number_ten));



        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currWord = words.get(position);
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, currWord.getmMediaResourceId());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                        mediaPlayer = null;
//                        Toast.makeText(NumbersActivity.this, "Memory released!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}