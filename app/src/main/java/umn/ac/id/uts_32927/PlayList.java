package umn.ac.id.uts_32927;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlayList extends AppCompatActivity {
    RecyclerView songView;
    ArrayList<PlayListModel> VH;
    Adapter myAdapter;
    FloatingActionButton settingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        settingBtn = findViewById(R.id.Setting);
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setup();
            }
        });
        songView = findViewById(R.id.recyclerView);
        display();
        openDialog();
    }


    public void setup(){
        SettingDialog dialog = new SettingDialog();
        dialog.show(getSupportFragmentManager(), "Setting !");
    }

    public void openDialog(){
        AnounceDialog dialog = new AnounceDialog();
        dialog.show(getSupportFragmentManager(), "Logged In !");
    }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null ,null, null);

        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int path = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do{
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                String currentpath = songCursor.getString(path);
                int currentpos = songCursor.getPosition();
                PlayListModel model = new PlayListModel(currentpos,currentTitle, currentArtist, currentpath);
                VH.add(model);
            } while (songCursor.moveToNext());
        }
    }

    void display(){
        VH = new ArrayList<>();
        getMusic();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        songView.setLayoutManager(layoutManager);

        //initialize MainAdapter
        myAdapter = new Adapter(PlayList.this, VH);
        //set MainAdapter to RecyclerView
        songView.setAdapter(myAdapter);
    }

}