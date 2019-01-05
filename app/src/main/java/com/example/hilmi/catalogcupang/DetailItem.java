package com.example.hilmi.catalogcupang;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.GenericDraweeView;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.net.URI;

import static android.R.id.list;
import static com.example.hilmi.catalogcupang.R.attr.fadeDuration;
import static com.example.hilmi.catalogcupang.R.attr.placeholderImage;
import static com.example.hilmi.catalogcupang.R.attr.progressBarImage;
import static com.example.hilmi.catalogcupang.R.attr.retryImage;
import static java.security.AccessController.getContext;

public class DetailItem extends AppCompatActivity {


    ImageView imGambar;

    TextView tvTitle;
    TextView tvHarga;
    TextView tvDeskripsi;

    MenuAdapter menu_adapter;
    MenuParceble menu;

    //full screen image
    GenericDraweeView simpleDrawee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        imGambar = (ImageView) findViewById(R.id.tampilImage);
        tvTitle = (TextView) findViewById(R.id.tampilTitle);
        tvHarga = (TextView) findViewById(R.id.tampilHarga);
        tvDeskripsi = (TextView) findViewById(R.id.tampilDeskripsi);


        menu_adapter = new MenuAdapter(this);

        //key dari MenuAdapter
        menu = getIntent().getParcelableExtra("key_menu");
        Glide.with(getApplication())
                .load(menu.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(imGambar);


        tvTitle.setText(menu.getTitle());
        tvHarga.setText(menu.getHarga());
        tvDeskripsi.setText(menu.getDeskripsi());






    }
}