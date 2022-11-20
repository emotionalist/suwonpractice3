package com.cookandroid.suwonpractice3;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RecyclerViewFragment extends Fragment {
    private ArrayList<imageList> stores = new ArrayList<>();
   // private RecyclerViewAdapter mAdapter;
    private ArrayList<St> arrayList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private SearchView searchView;

    Button sortname, sortrecent, sortstar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        searchView = (SearchView) v.findViewById(R.id.searchView);
        searchView.clearFocus();
        arrayList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(arrayList);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Store");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot sn : snapshot.getChildren()){
                    St st = sn.getValue(St.class);
                    arrayList.add(st);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        ActionBar actionBar = ((StartActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("전체목록");


        sortname = v.findViewById(R.id.sortname);
        sortrecent = v.findViewById(R.id.sortrecent);
        sortstar = v.findViewById(R.id.sortstar);

        sortname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<St> nameASC = new Comparator<St>() {
                    @Override
                    public int compare(St item1, St item2) {
                        return item1.getName().compareTo(item2.getName());
                    }
                };
                Collections.sort(arrayList, nameASC);
                adapter.notifyDataSetChanged();
            }
        });

        sortrecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<St> recentASC = new Comparator<St>() {
                    @Override
                    public int compare(St item1, St item2) {
                        return item1.getNum() - item2.getNum();
                    }
                };
                Collections.sort(arrayList, recentASC);
                adapter.notifyDataSetChanged();
            }
        });

        sortstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<St> starDESC = new Comparator<St>() {
                    @Override
                    public int compare(St item1, St item2) {
                        return item2.getStar().compareTo(item1.getStar());
                    }
                };
                Collections.sort(arrayList, starDESC);
                adapter.notifyDataSetChanged();
            }
        });

        setHasOptionsMenu(true);

        return v;
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.develope:
                Intent intent = new Intent(getActivity(), reportActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void filterList(String text){
        ArrayList<St> filteredList = new ArrayList<>();
        for(St store : arrayList){
            if(store.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(store);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.setFilteredList(filteredList);
        }
    }
}
