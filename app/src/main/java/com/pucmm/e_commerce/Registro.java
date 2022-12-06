package com.pucmm.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends Activity {


    EditText name, username, email, password, repassword;
    Button signup, forgotpassword, login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        //registro
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        forgotpassword = (Button) findViewById(R.id.btnforgotpassword);
        login = (Button) findViewById(R.id.btnlogin);
        DB = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = name.getText().toString();
                String user = username.getText().toString();
                String correo = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(nombre.equals("")||user.equals("")||correo.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Registro.this,"Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Registro.this, "Usted se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Registro.this,"El registro no pudo ser completado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Registro.this, "Este usuario ya existe. Inicia sesión.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registro.this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IniciarSesion.class);
                startActivity(intent);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Password.class);
                startActivity(intent);
            }
        });
    }
}