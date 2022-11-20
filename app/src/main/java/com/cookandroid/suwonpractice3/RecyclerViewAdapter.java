package com.cookandroid.suwonpractice3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<St> arrayList;
    private ArrayList<imageList> stores = new ArrayList<>();

    //private RecyclerViewFragment context;

    public RecyclerViewAdapter(ArrayList<St> arrayList){

        this.arrayList = arrayList;
        //this.context = context;
    }

    public void setFilteredList(ArrayList<St> filteredList){
        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView storename, storedesc, storestar;
        public ImageView storeimg;
        public LinearLayout container;



        public MyViewHolder(View view){
            super(view);
            storename = (TextView) view.findViewById(R.id.listtitle);
            storedesc = (TextView) view.findViewById(R.id.listdescribe);
            storestar = (TextView) view.findViewById(R.id.liststar);
           storeimg = (ImageView) view.findViewById(R.id.listimage);
            container = (LinearLayout) view.findViewById(R.id.layout_container);
        }
   }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Glide.with(holder.itemView).load(storage.getReferenceFromUrl(String.valueOf(arrayList.get(position).getImage()))).override(200,200).into(holder.storeimg); // IMG 받음
        holder.storename.setText(arrayList.get(position).getName());
        holder.storedesc.setText(arrayList.get(position).getDesc());
        holder.storestar.setText(arrayList.get(position).getStar());
        // stores.add(new imageList(0, R.drawable.image0));
        holder.storeimg.setImageResource(R.drawable.image0);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mPosition = holder.getAdapterPosition();
                Context context = v.getContext();
                Intent intent = new Intent(context, StoreActivity.class);
                intent.putExtra("storeName", arrayList.get(mPosition).Name);
              //  intent.putExtra("storePicture", arrayList.get(mPosition).Image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}