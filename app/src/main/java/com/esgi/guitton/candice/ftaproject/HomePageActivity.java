package com.esgi.guitton.candice.ftaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePageActivity extends AppCompatActivity {
    EditText edit_login;
    EditText edit_password;
    Button buttonConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        edit_login = findViewById(R.id.edit_login);
        edit_password = findViewById(R.id.edit_password);
        buttonConnect = findViewById(R.id.connexion_button);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);            }
        });
        //connexion avec

    }
}
