package com.example.demorecycleviewwithdatabinding.bindingAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MyBindingAdapter {
    /*
     * Use the flwg in xml-layout file
     * app:image_uri_adapter="@{product.image_uri}"
     * app:image_uri_error="@{@drawable/ic_launcher_background}"
     * app:image_uri_placeholder="@{@drawable/ic_launcher_background}"
     */
    @BindingAdapter({"image_uri_adapter", "image_uri_placeholder", "image_uri_error"})
    public static void loadImageUri(ImageView view, String uri, Drawable placeholder, Drawable error) {
        Context context =  view.getContext();
        RequestOptions requestOptions = new RequestOptions().placeholder(placeholder).error(error);
        Glide.with(context).asBitmap().load(uri).apply( requestOptions).into(view);
    }
}
