package com.example.socialtemplate.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.socialtemplate.R;
import com.example.socialtemplate.util.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.loginButton);
        final Context context = this;
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Utils.isLogged(context)){
                    Intent intent = new Intent(context,HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Utils.setLogin(context,true);
                    Intent intent = new Intent(context,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
