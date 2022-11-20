package com.cookandroid.suwonpractice3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, joinmem, findPw;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseUser user;
    //private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //this.getSupportActionBar().hide();

        joinmem = findViewById(R.id.joinmem);

        joinmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinMemberActivity.class);
                startActivity(intent);
            }
        });

        findPw = findViewById(R.id.findPw);

        findPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Findpw.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.userEmail);
        editTextPassword = (EditText) findViewById(R.id.userPW);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextEmail.getText().toString().equals("")
                        && !editTextPassword.getText().toString().equals("")) {
                    loginUser(editTextEmail.getText().toString(),
                            editTextPassword.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "계정과 비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth mAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };*/
    }


    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(MainActivity.this, "존재하지 않는 이메일 입니다.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(MainActivity.this, "이메일 형식이 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(MainActivity.this, "네트워크에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "로그인 성공!" + user.getEmail() + user.getUid(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(MainActivity.this, StartActivity.class));
                            finish();
                        }
                        /*if (task.isSuccessful()) {
                            // 로그인 성공
                            mAuth.addAuthStateListener(firebaseAuthListener);

                            Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, StartActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        }

        /*@Override
        protected void onStop() {
            super.onStop();
            if (firebaseAuthListener != null) {
                mAuth.removeAuthStateListener(firebaseAuthListener);
            }
        }*/
    }
}