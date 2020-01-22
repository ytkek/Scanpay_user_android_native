package ths.ScanPay_User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button signup,login;
    EditText postalcode;
    TextView recoverypasswordbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        postalcode = (EditText)findViewById(R.id.postalcode);
        postalcode.setEnabled(false);


        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(),SignUpStep1.class);
                startActivity(signup);
            }
        });

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainactivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainactivity);
            }
        });

        recoverypasswordbtn = (TextView)findViewById(R.id.recoverypasswordbtn);
        recoverypasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getApplicationContext(),RecoveryPasswordActivity.class);
                startActivity(lol);
            }
        });

    }
}
