package com.pucmm.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends AppCompatActivity {

    EditText email;
    Button reset;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        email = (EditText) findViewById(R.id.email_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = email.getText().toString();
                Boolean checkuser = DB.checkusername(correo);

                if(checkuser==true)
                {
                    Intent intent = new Intent(getApplicationContext(), Reset.class);
                    intent.putExtra("email", correo);
                    startActivity(intent);
                }else{
                    Toast.makeText(Password.this, "El usuario con el correo ingresa no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}