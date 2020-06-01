package ths.ScanPay_User;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ths.ScanPay_User.PostFunction.PostVerification_Validate_LoginID;

public class Verification_LoginPage extends AppCompatActivity {

    ImageView backbtn;
public static TextView checkloginresult,checkemailresult,otp_result;
public static EditText loginidedit,emailedit,otpedit;
public static Button sendotpbtn,loginbtn;
public static Activity verificationpage;

public static String systemOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificationlogin);

        verificationpage= this;

        checkloginresult = (TextView)findViewById(R.id.checkloginresult);
        checkemailresult = (TextView)findViewById(R.id.checkemailresult);
        otp_result = (TextView)findViewById(R.id.otp_result);

        checkloginresult.setVisibility(View.GONE);
        checkemailresult.setVisibility(View.GONE);
        otp_result.setVisibility(View.GONE);

        loginidedit = (EditText)findViewById(R.id.loginid_edit);
        emailedit = (EditText)findViewById(R.id.emailedit);
        otpedit = (EditText)findViewById(R.id.otpedit);

        loginbtn = (Button)findViewById(R.id.loginbtn);
        loginbtn.setVisibility(View.GONE);

        sendotpbtn = (Button)findViewById(R.id.sendotpbtn);
        sendotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!loginidedit.getText().toString().equals("")&&!emailedit.getText().toString().equals(""))
                {
                    new PostVerification_Validate_LoginID(verificationpage).execute("60"+loginidedit.getText().toString());
                    checkloginresult.setVisibility(View.GONE);

                }
                else
                {
                    checkloginresult.setVisibility(View.VISIBLE);
                    checkloginresult.setText("LoginID and Email Cannot be Empty");
                }

            }
        });

        backbtn =  (ImageView)findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    public static void countresend()
    {
        new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                sendotpbtn.setText( String.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) );
                sendotpbtn.setEnabled(false);
            }

            public void onFinish() {
                sendotpbtn.setText("RESEND");
                sendotpbtn.setEnabled(true);
            }
        }.start();


    }
}
