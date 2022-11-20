package com.cookandroid.suwonpractice3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyPageFragment extends Fragment {

    private FirebaseAuth mAuth;
    private TextView toolbar_text;

    Button develope, will, have, logout;

    public static MyPageFragment newInstance() {
        MyPageFragment fragment = new MyPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    private void signOut() {
        mAuth.getInstance().signOut();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager fm = getActivity().getSupportFragmentManager();


        ActionBar actionBar = ((StartActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("마이페이지");
        View rootView = inflater.inflate(R.layout.fragment_my_page, container, false);

        develope = rootView.findViewById(R.id.develope);
        will = rootView.findViewById(R.id.will);
        have = rootView.findViewById(R.id.have);
        logout = rootView.findViewById(R.id.logout);

        develope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), reportActivity.class);
                startActivity(intent);
            }
        });

        will.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), willActivity.class);
                startActivity(intent2);
            }
        });

        have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity(), haveActivity.class);
                startActivity(intent3);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

                Toast.makeText(getActivity(), "로그아웃 성공", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent4);
            }
        });

        return rootView;
    }

    /*@Override
    public void onViewCreated(@Nonnull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view == mlogout)
                    signOut();

                Toast.makeText(getActivity(),"로그아웃 성공",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }*/
}