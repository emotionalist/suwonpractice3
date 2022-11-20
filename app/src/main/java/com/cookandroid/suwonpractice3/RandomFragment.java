package com.cookandroid.suwonpractice3;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RandomFragment extends Fragment {
    Random random;
    Button choicebtn;
    RadioGroup rgroup;
    RadioButton rbstore, rbmenu;
    CardView cardview1, cardview2, cardview3;
    ImageView cardimage1, cardimage2, cardimage3;
    TextView cardtitle, carddesc, cardtitle2, carddesc2;

    String[] storename = {"에이", "비", "씨", "에프"};
    String[] storemenu = {"샐러드", "치킨", "피자", "햄버거"};
    Integer[] storepicture = {R.drawable.salad, R.drawable.chicken, R.drawable.pizza, R.drawable.hamburger};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_random, container, false);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        ActionBar actionBar = ((StartActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("랜덤");
        // 액션바에 뒤로가기 버튼 만들기
        // actionBar.setDisplayHomeAsUpEnabled(false);


        // Inflate the layout for this fragment
        random = new Random();
        choicebtn = rootView.findViewById(R.id.choicebtn);
        rgroup = rootView.findViewById(R.id.rgroup);
        rbmenu = rootView.findViewById(R.id.rbmenu);
        rbstore = rootView.findViewById(R.id.rbstore);
        cardview1 = rootView.findViewById(R.id.cardview1);
        cardview2 = rootView.findViewById(R.id.cardview2);
        cardview3 = rootView.findViewById(R.id.cardview3);
        cardimage1 = rootView.findViewById(R.id.cardimage1);
        cardimage2 = rootView.findViewById(R.id.cardimage2);
        cardimage3 = rootView.findViewById(R.id.cardimage3);
        cardtitle =rootView. findViewById(R.id.cardtitle);
        carddesc = rootView.findViewById(R.id.carddesc);
        cardtitle2 =rootView. findViewById(R.id.cardtitle2);
        carddesc2 = rootView.findViewById(R.id.carddesc2);

        choicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = random.nextInt(4);
                //메뉴별, 가게별 추천? 어떤식으로 할건지 정한 다음에 다시
                switch (rgroup.getCheckedRadioButtonId()) {
                    case R.id.rbstore:
                        cardview1.setVisibility(View.VISIBLE);
                        cardview2.setVisibility(View.INVISIBLE);
                        cardview3.setVisibility(View.INVISIBLE);
                        cardtitle.setText(storename[num]);
                        cardimage1.setImageResource(storepicture[num]);
                        break;
                    case R.id.rbmenu:
                        cardview2.setVisibility(View.VISIBLE);
                        cardview3.setVisibility(View.INVISIBLE);
                        cardview1.setVisibility(View.INVISIBLE);
                        carddesc.setText(storemenu[num]);
                        cardimage2.setImageResource(storepicture[num]);
                        break;
                    case R.id.rbmood:
                        String[] items = new String[]{"달달한 음식", "매운 음식", "느끼한 음식", "따뜻한 음식", "시원한 음식"};
                        int[] selectIndex = {0};

                        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                        dlg.setTitle("원하는 음식 종류를 선택하세요.")
                                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectIndex[0] = which;
                                    }
                                })
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(), items[selectIndex[0]], Toast.LENGTH_SHORT).show();
                                        cardview3.setVisibility(View.VISIBLE);
                                        cardview1.setVisibility(View.INVISIBLE);
                                        cardview2.setVisibility(View.INVISIBLE);
                                    }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getActivity(), "선택 취소하기", Toast.LENGTH_SHORT).show();
                                    }
                                }).create().show();
                        break;
                }

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
        inflater.inflate(R.menu.menurandom, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.reset:
                cardview1.setVisibility(View.INVISIBLE);
                cardimage1.setImageResource(R.drawable.questionmark);
                cardview2.setVisibility(View.INVISIBLE);
                cardimage2.setImageResource(R.drawable.questionmark);
                cardview3.setVisibility(View.INVISIBLE);
                cardimage3.setImageResource(R.drawable.questionmark);
                cardtitle.setText("가게이름");
                carddesc.setText("음식종류");
                cardtitle2.setText("가게이름");
                carddesc2.setText("음식종류");
                rgroup.clearCheck();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}