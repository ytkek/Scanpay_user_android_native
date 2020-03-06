package ths.ScanPay_User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_User.PostFunction.PostSignUp_Update_PaymentInfo_Task;

public class SignUpStep3 extends AppCompatActivity {

    EditText pin1,pin2,pin3,pin4,pin5,pin6;
    CheckBox agreementcheckbox;
    Button nextbtn,showbtn;
    TextView agreementbtn;
    ImageView backbtn,image_btn;
    Activity SignUpStep3;
    boolean indicator=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step3);

        SignUpStep3=this;
        backbtn = (ImageView)findViewById(R.id.backbtn);

        pin1 = (EditText) findViewById(R.id.pin1);
        pin2 = (EditText) findViewById(R.id.pin2);
        pin3 = (EditText) findViewById(R.id.pin3);
        pin4 = (EditText) findViewById(R.id.pin4);
        pin5 = (EditText) findViewById(R.id.pin5);
        pin6 = (EditText) findViewById(R.id.pin6);
        image_btn=(ImageView)findViewById(R.id.image_btn);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(indicator==false)
                {
                    image_btn.setBackgroundResource(R.drawable.open_eye);
                    indicator=true;
                    pin1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pin2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pin3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pin4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pin5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pin6.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                }
                else if (indicator==true)
                {
                    image_btn.setBackgroundResource(R.drawable.close_eye);
                    indicator=false;
                    pin1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pin2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pin3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pin4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pin5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pin6.setTransformationMethod(PasswordTransformationMethod.getInstance());


                }

            }
        });

        agreementcheckbox = (CheckBox)findViewById(R.id.agreementcheckbox);

        nextbtn = (Button)findViewById(R.id.nextbtn);
        nextbtn.setEnabled(false);
        nextbtn.setAlpha(.1f);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalpin=pin1.getText().toString()+pin2.getText().toString()+pin3.getText().toString()+pin4.getText().toString()+pin5.getText().toString()+pin6.getText().toString();
                new PostSignUp_Update_PaymentInfo_Task(SignUpStep3).execute(totalpin,SignUpStep1.MobileNum,SignUpStep1.systemOTP);
            }
        });
        agreementbtn = (TextView)findViewById(R.id.viewagreementbtn);

        agreementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewagreement = new Intent(getApplicationContext(),ViewAgreementWebview.class);
                startActivity(viewagreement);
            }
        });

        pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {
                    pin2.requestFocus();
                    checkinformation();
                }
            }
        });

        pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {
                    pin3.requestFocus();
                    checkinformation();
                }
            }
        });
        pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {
                    pin4.requestFocus();
                    checkinformation();
                }
            }
        });

        pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {
                    pin5.requestFocus();
                    checkinformation();
                }
            }
        });

        pin5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }



            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {
                    pin6.requestFocus();
                    checkinformation();
                }
            }
        });
        pin6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0)
                {

                    checkinformation();
                }
            }

        });
        pin1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspac
                checkinformation();
                }
                return false;
            }
        });
        pin2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(pin2.getText().toString().equals(""))
                    {
                        checkinformation();
                        pin1.requestFocus();
                    }

                }
                return false;
            }
        });
        pin3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(pin3.getText().toString().equals(""))
                    {
                        checkinformation();
                        pin2.requestFocus();
                    }

                }
                return false;
            }
        });

        pin4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(pin4.getText().toString().equals(""))
                    {
                        checkinformation();
                        pin3.requestFocus();
                    }

                }
                return false;
            }
        });

        pin5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(pin5.getText().toString().equals(""))
                    {
                        checkinformation();
                        pin4.requestFocus();
                    }

                }
                return false;
            }
        });
        pin6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if(pin6.getText().toString().equals(""))
                    {
                        checkinformation();
                        pin5.requestFocus();
                    }

                }
                return false;
            }
        });



        agreementcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              checkinformation();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void checkinformation()
    {
        if(agreementcheckbox.isChecked()&&!pin1.getText().toString().equals("")&&!pin2.getText().toString().equals("")&&!pin3.getText().toString().equals("")&&!pin4.getText().toString().equals("")&&!pin5.getText().toString().equals("")&&!pin6.getText().toString().equals(""))
        {
            nextbtn.setAlpha(1f);
            nextbtn.setEnabled(true);
        }
        else
        {
            nextbtn.setAlpha(.1f);
            nextbtn.setEnabled(false);
        }
    }
}
