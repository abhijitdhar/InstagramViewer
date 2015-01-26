package com.adapp.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhidhar on 1/22/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {


    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);

        tvCaption.setText(photo.getCaption());
        imgPhoto.getLayoutParams().height = photo.getImageHeight();

        // reset image from view
        imgPhoto.setImageResource(0);

        Picasso.with(getContext()).load(photo.getImageUrl()).into(imgPhoto);


        return convertView;


    }
}
