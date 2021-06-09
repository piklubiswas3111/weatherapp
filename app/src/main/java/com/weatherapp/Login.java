package com.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText username, password;
    private Button login;
    private String Admin = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });


    }

    private void Validation() {

        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter your UserId");
            return;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please enter your Password");
            return;
        } else {

            if (username.getText().toString().toLowerCase().equals(Admin)) {
                if (password.getText().toString().toLowerCase().equals(Admin)){
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
            }else {
                Toast.makeText(getApplicationContext(),"Enter correct password or username",Toast.LENGTH_SHORT).show();
            }


        }
    }
}