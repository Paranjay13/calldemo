package com.pgbsolution.calllogsdemo;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by paran on 5/22/2017.
 */

public class Adapter extends ArrayAdapter<CallLogCustom> {
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<CallLogCustom> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view, parent, false);
        TextView state= (TextView) convertView.findViewById(R.id.state);
        TextView name= (TextView) convertView.findViewById(R.id.name);
        TextView time= (TextView) convertView.findViewById(R.id.time);
        ImageView mediabutton= (ImageView) convertView.findViewById(R.id.mediabutton);
        CallLogCustom callLogCustom=getItem(position);
        state.setText(callLogCustom.getState().equals("in")?"Incoming":"Outgoing");
        name.setText(callLogCustom.getName());
        SimpleDateFormat sdf1=new SimpleDateFormat("h:mm a");
        SimpleDateFormat sdf2=new SimpleDateFormat("h:mm a - d/M/yyyy");
        String timeval=sdf1.format(callLogCustom.getTimestamp()) + " to "+sdf2.format(callLogCustom.getDuration());
        time.setText(timeval);
        final String path=callLogCustom.getFilePath();
        mediabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main2Activity)getContext()).playfile(path);
            }
        });
        animate(convertView);
        return convertView;
    }

    private void animate(View view) {
        Animation movedown= AnimationUtils.loadAnimation(getContext(),
                R.anim.move);
        view.startAnimation(movedown);
        view.setVisibility(View.VISIBLE);
    }


}
