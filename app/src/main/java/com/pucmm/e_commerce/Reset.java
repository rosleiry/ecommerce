package com.pucmm.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reset extends AppCompatActivity {

    TextView email;
    EditText pass, repass;
    Button confirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);


        email = (TextView) findViewById(R.id.email_reset_text);
        pass = (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = email.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {


                    Boolean checkpasswordupdate = DB.updatepassword(correo, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), IniciarSesion.class);
                        startActivity(intent);
                        Toast.makeText(Reset.this, "La contrasena ha sido actualizada con exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Reset.this, "No se ha podido actualizar la contrasena", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Reset.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}