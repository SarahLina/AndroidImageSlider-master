package com.example.msia.julina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.R;

import Services.LoginTask;
import Services.UpdateDispoTask;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login (View view)
    {
        EditText username= (EditText)findViewById(R.id.username);
        EditText password= (EditText)findViewById(R.id.password);


        String user= username.getText().toString().trim();
        String pass = password.getText().toString().trim();


        new LoginTask(this).execute(user,pass);
    }
}
