package com.adapp.instagramviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.NumberFormat;
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

        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
        ImageView imgProfile = (ImageView) convertView.findViewById(R.id.imgProfile);

        tvUsername.setText(photo.getUsername());
        tvCaption.setText(photo.getCaption());
        int likesCount = photo.getLikesCount();
        String likesCountStr = NumberFormat.getInstance().format(likesCount);
        tvLikes.setText(likesCountStr + " likes");

        Drawable dr = getContext().getResources().getDrawable(R.drawable.heart_icon);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getContext().getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));

        tvLikes.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

        //tvLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.heart_icon, 0, 0, 0);

        //tvLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_check_material, 0, 0, 0);

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(3)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        imgProfile.setImageResource(0);
        Picasso.with(getContext()).load(photo.getProfilePicture()).resize(50, 50).centerCrop().into(imgProfile);
        //Picasso.with(getContext()).load(photo.getProfilePicture()).fit().transform(transformation).into(imgProfile);

        imgPhoto.getLayoutParams().height = photo.getImageHeight();
        // reset image from view
        imgPhoto.setImageResource(0);

        Picasso.with(getContext()).load(photo.getImageUrl()).into(imgPhoto);


        return convertView;


    }
}
