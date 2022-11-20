package com.cookandroid.suwonpractice3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {
    private Context context;
    private List list;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
    }

    class ViewHolder{
        public ImageView storeimg;
        public TextView storename;
        public TextView storedesc;
        public TextView storestar;
    }

    public ListViewAdapter(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.listitem, parent, false);
        }

        viewHolder = new ViewHolder();
        viewHolder.storeimg = (ImageView) convertView.findViewById(R.id.listimage);
        viewHolder.storename = (TextView) convertView.findViewById(R.id.listtitle);
        viewHolder.storedesc = (TextView) convertView.findViewById(R.id.listdescribe);
        viewHolder.storestar = (TextView) convertView.findViewById(R.id.liststar);

        final Store store = (Store) list.get(position);
        viewHolder.storeimg.setImageResource(store.getStoreimg());
        viewHolder.storename.setText(store.getStorename());
        viewHolder.storedesc.setText(store.getStoredesc());
        viewHolder.storestar.setText(store.getStorestar());

        return convertView;

    }


}
