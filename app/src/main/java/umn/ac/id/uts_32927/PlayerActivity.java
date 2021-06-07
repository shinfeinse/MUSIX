package umn.ac.id.uts_32927;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    Button nextBtn, prevBtn ,playBtn;
    TextView judulTv, artistTv;
    SeekBar songSeekBar;
    Thread updateseekBar;
    Handler handler = new Handler();
    static MediaPlayer myMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        nextBtn = findViewById(R.id.btnNext);
        prevBtn = findViewById(R.id.btnPrev);
        playBtn = findViewById(R.id.btnPlay);

        judulTv = findViewById(R.id.tvJudul);
        artistTv = findViewById(R.id.tvArtist);

        songSeekBar = findViewById(R.id.seekBar);

        updateseekBar = new Thread(){
            @Override
            public void run(){
                int totalDuration = myMediaPlayer.getDuration();
                int currentPosition = 0;
                while (currentPosition<totalDuration){
                    try{
                        sleep(500);
                        currentPosition = myMediaPlayer.getCurrentPosition();
                        songSeekBar.setProgress(currentPosition);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                };
            }
        };

        if(myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String intentJudul = bundle.getString("Judul");
        String intentArtist = bundle.getString("Artist");
        String path = bundle.getString("Path");
        int position = bundle.getInt("Pos");
        judulTv.setText(intentJudul);
        artistTv.setText(intentArtist);
        judulTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        judulTv.setSelected(true);
        Uri uri = Uri.parse(path);
        myMediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        myMediaPlayer.start();
        playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        songSeekBar.setMax(myMediaPlayer.getDuration());
        updateseekBar.start();

        songSeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.purple_500), PorterDuff.Mode.MULTIPLY);
        songSeekBar.getThumb().setColorFilter(getResources().getColor(R.color.purple_500),PorterDuff.Mode.SRC_IN);


        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null , null, null);
        songCursor.moveToPosition(position);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                songCursor.moveToNext();
                if(songCursor != null){
                    int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int path = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                    String nextSongTitle = songCursor.getString(songTitle);
                    String nextSongArtist = songCursor.getString(songArtist);
                    String nextPath = songCursor.getString(path);

                    judulTv.setText(nextSongTitle);
                    artistTv.setText(nextSongArtist);
                    Uri uri = Uri.parse(nextPath);

                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                    myMediaPlayer.start();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    songSeekBar.setMax(myMediaPlayer.getDuration());
                }
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                songCursor.moveToPrevious();
                if(songCursor != null){
                    int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int path = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                    String nextSongTitle = songCursor.getString(songTitle);
                    String nextSongArtist = songCursor.getString(songArtist);
                    String nextPath = songCursor.getString(path);

                    judulTv.setText(nextSongTitle);
                    artistTv.setText(nextSongArtist);
                    Uri uri = Uri.parse(nextPath);

                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                    myMediaPlayer.start();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    songSeekBar.setMax(myMediaPlayer.getDuration());
                }
            }
        });

        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                   myMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myMediaPlayer.isPlaying()){
                    myMediaPlayer.pause();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }else{
                    myMediaPlayer.start();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myMediaPlayer.isPlaying()){
                    myMediaPlayer.pause();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }else{
                    myMediaPlayer.start();
                    playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });
    }
}