package com.pgbsolution.calllogsdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView list;
    List<CallLogCustom> calls;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutfile);
        list=(ListView) findViewById(R.id.listview);
        calls=new DBHelper(getApplicationContext()).readCallLogs();
        adapter=new Adapter(Main2Activity.this,R.layout.adapter_view,calls);
        list.setAdapter(adapter);
        if(calls.size()!=0){
            list.setVisibility(View.VISIBLE);
        }
        Log.d("size",String.valueOf(calls.size()));
    }

    public void playfile(String path) {

            //set up MediaPlayer
            MediaPlayer mp = new MediaPlayer();

            try {
                mp.setDataSource(path);
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
