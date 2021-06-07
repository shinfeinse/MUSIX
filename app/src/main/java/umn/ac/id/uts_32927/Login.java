package umn.ac.id.uts_32927;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button loginBtns;
    private EditText usernameEt, passwordEt;
    private TextView tvAnon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEt = findViewById(R.id.etUsername);
        passwordEt = findViewById(R.id.etPassword);
        tvAnon = findViewById(R.id.Anouncement);
        loginBtns = findViewById(R.id.Logon);
        loginBtns.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Login();
            }
        });
    }

    public void Login(){
        if(usernameEt.getText().toString().equals("uasmobile")) {
            if(passwordEt.getText().toString().equals("uasmobilegenap")) {
                Intent intent = new Intent(this, PlayList.class);
                startActivity(intent);
            } else {
                tvAnon.setText("Password Anda Salah !");
            }
        } else {
            tvAnon.setText("Username Anda Salah !");
        }
    }
}