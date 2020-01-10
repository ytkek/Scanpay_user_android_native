package ths.ScanPay_User;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class SignUpStep1 extends AppCompatActivity {

    Button verifybtn,resendbtn,nextbtn;
    LinearLayout otplayout,passwordlayout,reconfirmpasswordlayout;
    EditText mobileedittext,otp1,otp2,otp3,otp4,otp5,otp6,passwordedit,confirmpasswordedit;
    boolean verifybool=false;
    TextView checkotpresult,password_error;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step1);

        backbtn = (ImageView)findViewById(R.id.backbtn);

        mobileedittext = (EditText)findViewById(R.id.mobile_edit);

        otplayout = (LinearLayout)findViewById(R.id.OTPlayout);
        otplayout.setVisibility(View.GONE);

        verifybtn = (Button)findViewById(R.id.verifyotp);
        verifybtn.setVisibility(View.INVISIBLE);

        resendbtn = (Button)findViewById(R.id.resend);


        otp1 = (EditText) findViewById(R.id.otp1);
        otp2 = (EditText) findViewById(R.id.otp2);
        otp3 = (EditText) findViewById(R.id.otp3);
        otp4 = (EditText) findViewById(R.id.otp4);
        otp5 = (EditText) findViewById(R.id.otp5);
        otp6 = (EditText) findViewById(R.id.otp6);

        checkotpresult = (TextView)findViewById(R.id.checkotpresult);
        checkotpresult.setVisibility(View.INVISIBLE);

        passwordlayout = (LinearLayout)findViewById(R.id.passwordlayout);
        passwordlayout.setVisibility(View.INVISIBLE);

        reconfirmpasswordlayout = (LinearLayout)findViewById(R.id.reconfirmpasswordlayout);
        reconfirmpasswordlayout.setVisibility(View.INVISIBLE);

        passwordedit = (EditText)findViewById(R.id.password_edit);

        confirmpasswordedit = (EditText)findViewById(R.id.confirmpassword_edit);

        password_error = (TextView)findViewById(R.id.password_error);
        password_error.setVisibility(View.INVISIBLE);

        nextbtn = (Button)findViewById(R.id.nextbtn);
        nextbtn.setEnabled(false);
        nextbtn.setAlpha(.1f);
        mobileedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().startsWith("0"))
                {


                    mobileedittext.setText("");
                }

                if(s.length()==9||s.length()==10)
                {
                    if(verifybool==false)
                    {
                        verifybtn.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        verifybtn.setVisibility(View.GONE);
                    }

                }
                else
                {
                    verifybtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifybool=true;

                    otplayout.setVisibility(View.VISIBLE);
                    verifybtn.setVisibility(View.GONE);
                    countresend();
                    sendOTP();


            }
        });
        resendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countresend();
                sendOTP();
            }
        });

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0)
                {
                    otp2.requestFocus();
                }
                if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                {

                    checkOTP();

                }
                else
                {
                    checkotpresult.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0)
                {
                    otp3.requestFocus();
                }
                if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                {

                    checkOTP();

                }
                else
                {
                    checkotpresult.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0)
                {
                    otp4.requestFocus();
                }
                if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                {

                    checkOTP();

                }
                else
                {
                    checkotpresult.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0)
                {
                    otp5.requestFocus();
                }
                if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                {

                    checkOTP();

                }
                else
                {
                    checkotpresult.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()!=0)
                {
                    otp6.requestFocus();
                }
                if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                {

                    checkOTP();

                }
                else
                {
                    checkotpresult.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                    if(!otp1.getText().toString().equals("")&&!otp2.getText().toString().equals("")&&!otp3.getText().toString().equals("")&&!otp4.getText().toString().equals("")&&!otp5.getText().toString().equals("")&&!otp6.getText().toString().equals(""))
                    {

                        checkOTP();

                    }
                    else
                    {
                        checkotpresult.setVisibility(View.INVISIBLE);

                    }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        otp1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspac
                }
                return false;
            }
        });
        otp2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(otp2.getText().toString().equals(""))
                    {
                        otp1.requestFocus();
                    }

                }
                return false;
            }
        });
        otp3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(otp3.getText().toString().equals(""))
                    {
                        otp2.requestFocus();
                    }

                }
                return false;
            }
        });
        otp4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(otp4.getText().toString().equals(""))
                    {
                        otp3.requestFocus();
                    }

                }
                return false;
            }
        });
        otp5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(otp5.getText().toString().equals(""))
                    {
                        otp4.requestFocus();
                    }

                }
                return false;
            }
        });
        otp6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(otp6.getText().toString().equals(""))
                    {
                        otp5.requestFocus();
                    }

                }
                return false;
            }
        });

        passwordedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if(s.length()>=4)
                {
                    if(s.toString().matches("[a-zA-Z]+"))
                    {
                        password_error.setVisibility(View.VISIBLE);
                        password_error.setText("Should Contain Numbers");
                        reconfirmpasswordlayout.setVisibility(View.INVISIBLE);
                    }
                    else if (s.toString().matches("[0-9]+"))
                    {
                        password_error.setVisibility(View.VISIBLE);
                        password_error.setText("Should Contain Letters");
                        reconfirmpasswordlayout.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        password_error.setVisibility(View.INVISIBLE);
                        reconfirmpasswordlayout.setVisibility(View.VISIBLE);
                    }

                }
                else
                {
                    password_error.setVisibility(View.INVISIBLE);
                    reconfirmpasswordlayout.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmpasswordedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(confirmpasswordedit.getText().toString().equals(passwordedit.getText().toString()))
                {
                    nextbtn.setEnabled(true);
                    nextbtn.setAlpha(1f);
                }
                else
                {
                    nextbtn.setEnabled(false);
                    nextbtn.setAlpha(.1f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(),SignUpStep2.class);
                startActivity(next);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    public void countresend()
    {
        new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                        resendbtn.setText( String.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) );
                        resendbtn.setEnabled(false);
            }

            public void onFinish() {
                resendbtn.setText("RESEND");
                resendbtn.setEnabled(true);
            }
        }.start();


    }
    public void sendOTP()
    {

    }

    public void checkOTP()
    {
        boolean result=true;
        if(result==true)
        {
            checkotpresult.setVisibility(View.VISIBLE);
            checkotpresult.setText("correct");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    mobileedittext.setEnabled(false);
                    otplayout.setVisibility(View.GONE);
                    passwordlayout.setVisibility(View.VISIBLE);


                }
            }, 2000);

        }
        else
        {

            //checkotpresult.setText("wrong");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                   // otp1.setText("");
                   /// otp2.setText("");
                   // otp3.setText("");
                   // otp4.setText("");
                    //otp5.setText("");
                   // otp6.setText("");
                   // otp1.requestFocus();
                    checkotpresult.setVisibility(View.VISIBLE);
                    checkotpresult.setText("wrong");
                }
            }, 2000);

        }

    }

}
