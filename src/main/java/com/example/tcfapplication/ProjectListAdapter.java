package com.example.tcfapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends ArrayAdapter<Convertdata> {

    private Context mcontext;
    TextView Locationtxt,Campustxt;
    int mResource;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Location = getItem(position).getLocation();
        String Campusename = getItem(position).getCampuseName();
        Convertdata convertdata = new Convertdata(Location, Campusename);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource, parent, false);

        Locationtxt = convertView.findViewById(R.id.textview1);
        Campustxt = convertView.findViewById(R.id.textView2);

        Locationtxt.setText(Location);
        Campustxt.setText(Campusename);

        return convertView;
    }


    public ProjectListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Convertdata> objects) {
        super(context, resource, objects);
        mcontext=context;
        mResource=resource;


    }
}
