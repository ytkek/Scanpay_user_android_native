package ths.ScanPay_UserV5;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_UserV5.PostFunction.PostLogin_Recover_LoginID_Password_Task;

public class RecoveryPasswordActivity extends AppCompatActivity {

    ImageView backbtn;
    EditText code,loginid,email;
    public static TextView checklogin,checkemail,password_recover_result;
    Button sendpasswordbtn;
    Activity recoverypassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recoverypassword);

        recoverypassword=this;
        backbtn = (ImageView)findViewById(R.id.backbtn);
        code = (EditText)findViewById(R.id.code);
        code.setEnabled(false);
        loginid = (EditText)findViewById(R.id.loginid_edit);
        checklogin = (TextView)findViewById(R.id.checkloginresult) ;
        checklogin.setVisibility(View.INVISIBLE);
        email = (EditText)findViewById(R.id.emailedit);
        checkemail = (TextView)findViewById(R.id.checkemailresult);
        checkemail.setVisibility(View.INVISIBLE);
        password_recover_result = (TextView)findViewById(R.id.password_recover_result);
        password_recover_result.setVisibility(View.INVISIBLE);

        sendpasswordbtn = (Button)findViewById(R.id.sendpasswordbtn);
        sendpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginid.getText().toString().equals("")||email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Loginid or Email field is empty ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(checklogin.getVisibility()==View.INVISIBLE&&checkemail.getVisibility()==View.INVISIBLE)
                    {
                        new PostLogin_Recover_LoginID_Password_Task(recoverypassword).execute("60"+loginid.getText().toString(),email.getText().toString());

                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"Remain Error Message",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean emailvalidate =isEmailValid(email.getText().toString());
                if(emailvalidate==true)
                {
                    checkemail.setVisibility(View.INVISIBLE);
                    checkemail.setText("");

                }
                else
                {

                    checkemail.setVisibility(View.VISIBLE);
                    checkemail.setText("Email Wrong Format");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().startsWith("0"))
                {


                    loginid.setText("");
                }

                if(s.length()==9||s.length()==10)
                {

                    checklogin.setVisibility(View.INVISIBLE);
                    checklogin.setText("");
                }
                else
                {
                    checklogin.setVisibility(View.VISIBLE);
                    checklogin.setText("Please correct 9 ~ 10 digit login id");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } // end of TextWatcher (email)
}
