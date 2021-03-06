package ths.ScanPay_UserV5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_UserV5.PostFunction.PostSignUp_Update_User_Info_Task;

public class SignUpStep2 extends AppCompatActivity {

    EditText nameedit,emailedit;
    Button nextbtn;
    TextView error_message_textview;
    ImageView backbtn;
    Activity SignUpStep2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_step2);

        SignUpStep2=this;
        backbtn = (ImageView)findViewById(R.id.backbtn);
        nameedit = (EditText)findViewById(R.id.name_edit);
        emailedit = (EditText) findViewById(R.id.email_edit);
        nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setEnabled(false);
        nextbtn.setAlpha(.1f);
        error_message_textview = (TextView)findViewById(R.id.error_message_textview);
        error_message_textview.setVisibility(View.INVISIBLE);
        nameedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!nameedit.getText().toString().equals("")&&!emailedit.getText().toString().equals(""))
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

        emailedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                error_message_textview.setVisibility(View.INVISIBLE);
                error_message_textview.setText("");

                if(!nameedit.getText().toString().equals("")&&!emailedit.getText().toString().equals(""))
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


                if(!nameedit.getText().toString().equals("")&&!emailedit.getText().toString().equals(""))
                {
                    boolean emailvalidate =isEmailValid(emailedit.getText().toString());


                    if(emailvalidate==true)
                    {

                        new PostSignUp_Update_User_Info_Task(SignUpStep2).execute(nameedit.getText().toString(),emailedit.getText().toString(),SignUpStep1.MobileNum,SignUpStep1.systemOTP);

                    }
                    else
                    {

                        error_message_textview.setVisibility(View.VISIBLE);
                        error_message_textview.setText("Email Wrong Format");
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpStep2);
                        builder.setMessage("Error #B0012 Invaid Email")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                }
                else
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpStep2);
                    builder.setMessage("Error #B0011 Name or Email is Empty")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();



                }


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
