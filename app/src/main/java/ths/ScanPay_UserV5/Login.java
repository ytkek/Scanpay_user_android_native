package ths.ScanPay_UserV5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_UserV5.PostFunction.PostLogin_Validate_LoginID_Task;

public class Login extends AppCompatActivity {

    Button signup,login;
    EditText postalcode,idbox,passwordbox;
    TextView recoverypasswordbtn,verificationloginbtn;
    Activity loginactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginactivity=this;
        idbox=(EditText)findViewById(R.id.idbox);
        passwordbox = (EditText)findViewById(R.id.passwordbox);
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
                new PostLogin_Validate_LoginID_Task(loginactivity).execute("60"+idbox.getText().toString(),passwordbox.getText().toString());

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

        verificationloginbtn = (TextView)findViewById(R.id.verification_login);
        verificationloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getApplicationContext(),Verification_LoginPage.class);
                startActivity(lol);
            }
        });

    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
