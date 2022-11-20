package com.cookandroid.suwonpractice3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ListFragment extends Fragment {

    ListViewAdapter adapter;
    ArrayList<Store> stores;
    ListView listView;

    Button sortname, sortrecent, sortstar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ActionBar actionBar = ((StartActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("전체목록");

        stores = new ArrayList<>();
        stores.add(new Store(1, R.drawable.image1, "신동랩", "돈까스, 덮밥 등", "4.5점"));
        stores.add(new Store(2, R.drawable.image2, "여너여너", "연어 초밥", "4.1점"));
        stores.add(new Store(3, R.drawable.image3, "포히엔", "베트남 쌀국수 등", "3.7점"));
        stores.add(new Store(4, R.drawable.image4, "탄탄석쇠", "석쇠불고기 등", "3.5점"));
        stores.add(new Store(5, R.drawable.image5, "밥집", "라면, 김밥 등", "4.5점"));
        stores.add(new Store(6, R.drawable.image6, "정가네", "국밥, 부대찌개 등", "4.1점"));
        stores.add(new Store(7, R.drawable.image7, "와우곱창", "곱창 등", "3.7점"));
        stores.add(new Store(8, R.drawable.image8, "역전할맥", "여러가지 안주", "3.5점"));
        stores.add(new Store(9, R.drawable.image9, "폼프릿츠", "꿀맥, 감자튀김 등", "4.5점"));
        stores.add(new Store(10, R.drawable.image10, "노랑통닭", "치킨, 맥주 등", "4.1점"));
        stores.add(new Store(11, R.drawable.image11, "유쓰동", "규동, 가츠동 등", "3.7점"));
        stores.add(new Store(12, R.drawable.image12, "챠이챠이", "짜장면, 짬뽕 등", "3.5점"));
        stores.add(new Store(13, R.drawable.image13, "무공돈까스", "돈까스, 쫄면 등", "4.5점"));
        stores.add(new Store(14, R.drawable.image14, "술빛상회", "술, 육회 등", "4.1점"));
        stores.add(new Store(15, R.drawable.image15, "한신포차", "백종원 ㅇㅇ..", "3.7점"));
        stores.add(new Store(16, R.drawable.image16, "설빙", "빙수 빙수", "3.5점"));

        listView = (ListView) rootView.findViewById(R.id.list_custom);
        adapter = new ListViewAdapter(getContext(), stores);
        listView.setAdapter(adapter);

        sortname = rootView.findViewById(R.id.sortname);
        sortrecent = rootView.findViewById(R.id.sortrecent);
        sortstar = rootView.findViewById(R.id.sortstar);

        sortname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Store> nameASC = new Comparator<Store>() {
                    @Override
                    public int compare(Store item1, Store item2) {
                        return item1.getStorename().compareTo(item2.getStorename());
                    }
                };
                Collections.sort(stores, nameASC);
                adapter.notifyDataSetChanged();
            }
        });

        sortrecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Store> recentASC = new Comparator<Store>() {
                    @Override
                    public int compare(Store item1, Store item2) {
                        return item1.getStorenum() - item2.getStorenum();
                    }
                };
                Collections.sort(stores, recentASC);
                adapter.notifyDataSetChanged();
            }
        });

        sortstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Store> starDESC = new Comparator<Store>() {
                    @Override
                    public int compare(Store item1, Store item2) {
                        return item2.getStorestar().compareTo(item1.getStorestar());
                    }
                };
                Collections.sort(stores, starDESC);
                adapter.notifyDataSetChanged();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                startActivity(intent);
            }
        });


        setHasOptionsMenu(true);
        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menuaccount, menu);
        inflater.inflate(R.menu.menusearch, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.develope:
                Intent intent = new Intent(getActivity(), reportActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}