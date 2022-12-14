package com.cookandroid.suwonpractice3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class JoinMemberActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Button backToLogin, checkEmail;
    LinearLayout checkLayout;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinmember);
        //this.getSupportActionBar().hide();

        backToLogin = findViewById(R.id.backToLogin);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.userEmail);
        editTextPassword = (EditText) findViewById(R.id.userPW);
        Button SignUp = (Button) findViewById(R.id.registerBtn);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextEmail.getText().toString().equals("")
                        && !editTextPassword.getText().toString().equals("")) {
                    // ???????????? ??????????????? ????????? ?????? ??????
                    createUser(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                } else {
                    // ???????????? ??????????????? ????????? ??????
                    Toast.makeText(JoinMemberActivity.this, "????????? ??????????????? ???????????????", Toast.LENGTH_LONG).show();
                }
            }

            private void createUser(String email, String password) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(JoinMemberActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(JoinMemberActivity.this, "??????????????? ????????????.", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(JoinMemberActivity.this, "????????? ????????? ?????? ????????????.", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(JoinMemberActivity.this, "?????? ???????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(JoinMemberActivity.this, "???????????? ??????", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    user = mAuth.getCurrentUser();

                                    Toast.makeText(JoinMemberActivity.this, "???????????? ??????!" /*+ user.getEmail() + user.getUid()*/, Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(JoinMemberActivity.this, MainActivity.class));
                                    finish();
                                }
                                /*if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(JoinMemberActivity.this, "???????????? ??????!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(JoinMemberActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(JoinMemberActivity.this, "???????????? ??????", Toast.LENGTH_SHORT).show();
                            }*/
                            }
                        });
            }
        });
    }
}