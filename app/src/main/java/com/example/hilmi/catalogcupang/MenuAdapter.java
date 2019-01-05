package com.example.hilmi.catalogcupang;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hilmi on 19/12/2018.
 */

public class MenuAdapter  extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

        Context context;
        private List<MenuParceble> list;

public MenuAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        }


    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        final MenuAdapter.ViewHolder holder=new MenuAdapter.ViewHolder(v);




        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {

        final MenuParceble menu = list.get(holder.getAdapterPosition());


        holder.tvName.setText(menu.getTitle());
        holder.tvPrice.setText(menu.getHarga());


        Glide.with(context)
                .load(menu.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .into(holder.ivMenu);


        //klik holder
        holder.rKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_tampil = new Intent(context, DetailItem.class);
                i_tampil.putExtra("key_menu", menu);
                context.startActivity(i_tampil);
            }
        });
    }

//        //
//        //image di klik
//        // image jika di klik
//        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i_tampil = new Intent(context, DetailItem.class);
//                i_tampil.putExtra("key_menu", menu);
//                context.startActivity(i_tampil);
//            }
//        });
//    }
//


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void add(MenuParceble r) {
        list.add(r);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<MenuParceble> moveResults) {
        for (MenuParceble result : moveResults) {
            add(result);
        }
    }

    public void remove(MenuParceble r) {
        int position = list.indexOf(r);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void clearAll() {
        if (!list.isEmpty()){
            list.clear();
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public MenuParceble getItem(int position) {
        if (list !=null){
            return list.get(position);
        }
        return null;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivMenu;
        public TextView tvName;
        public TextView tvPrice;
        public RelativeLayout rKlik;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMenu = itemView.findViewById(R.id.tvImage);
            tvName = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvHarga);
              rKlik = itemView.findViewById(R.id.click_componen);

        }
    }
}