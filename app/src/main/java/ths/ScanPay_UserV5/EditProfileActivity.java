package ths.ScanPay_UserV5;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_UserV5.GetFunction.GetUserProfileListTask;
import ths.ScanPay_UserV5.PostFunction.PostUserProfile_OldPassword_Task;

public class EditProfileActivity extends AppCompatActivity {
    Activity editactivity;
    ImageView back;
    ImageView profile_img;
    TextView changefullnamebtn,changeidbtn,changepasswordbtn,
             changeemailbtn,changemobilebtn,changegenderbtn,
             changenicknamebtn,changeremarkbtn,changedobbtn,
             changepinbtn;
   public static  EditText changefullname,changeid,changepassword,changeemail,
             changemobile,changegender,changenickname,changeremark,
             changedob,changepin;

    TextView password_error_message,email_error_message,mobile_error_message,gender_error_message,pin_error_message;

    Button savebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        editactivity=this;

        new GetUserProfileListTask(this).execute(MainActivity.LoginID,MainActivity.Password);



        changefullname = (EditText)findViewById(R.id.fullnameedit);
        changefullname.setEnabled(false);

        changefullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changefullname.setEnabled(false);
                   // Toast.makeText(getApplicationContext(),"save full name",Toast.LENGTH_SHORT).show();

                }
            }
        });

        changefullnamebtn = (TextView)findViewById(R.id.changefullnamebtn);
        changefullnamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                //changefullname.setEnabled(true);
               // changefullname.requestFocus();
                changefullname.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changefullname, InputMethodManager.SHOW_IMPLICIT);

                EditProfileDialog cdd=new EditProfileDialog(editactivity,"fullname");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

        changeid =(EditText)findViewById(R.id.idedit);
        changeid.setEnabled(false);



        password_error_message = (TextView) findViewById(R.id.password_error_message_textview);
        password_error_message.setVisibility(View.INVISIBLE);

        changepassword = (EditText)findViewById(R.id.passwordedit);
        changepassword.setEnabled(false);

        changepassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changepassword.setEnabled(false);
                //    Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changepassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if(s.length()>=4)
                {
                    if(s.toString().matches("[a-zA-Z]+"))
                    {
                        password_error_message.setVisibility(View.VISIBLE);
                        password_error_message.setText("Should Contain Numbers");

                    }
                    else if (s.toString().matches("[0-9]+"))
                    {
                        password_error_message.setVisibility(View.VISIBLE);
                        password_error_message.setText("Should Contain Letters");
                    }
                    else
                    {
                        password_error_message.setVisibility(View.INVISIBLE);

                    }

                }
                else if (s.length()<4)
                {
                    password_error_message.setVisibility(View.VISIBLE);
                    password_error_message.setText("Should More than 4 char");

                }
                else
                {
                    password_error_message.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        changepasswordbtn = (TextView)findViewById(R.id.changepasswordbtn);
        changepasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changepassword.setEnabled(false);
               // changepassword.requestFocus();
              //  changepassword.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changepassword, InputMethodManager.SHOW_IMPLICIT);
                new PostUserProfile_OldPassword_Task(editactivity).execute(MainActivity.LoginID,MainActivity.LoginID,MainActivity.Password);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"password");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

        changeemail = (EditText)findViewById(R.id.emailedit);
        changeemail.setEnabled(false);


        changeemailbtn = (TextView)findViewById(R.id.changeemailbtn);
        changeemailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"email");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });


        changemobile = (EditText)findViewById(R.id.handphoneedit);
        changemobile.setEnabled(false);

        mobile_error_message = (TextView)findViewById(R.id.mobile_error_message_textview);
        mobile_error_message.setVisibility(View.INVISIBLE);

        changemobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().startsWith("0"))
                {


                    changemobile.setText("");
                }

                if(s.length()==8||s.length()==9||s.length()==10)
                {
                    mobile_error_message.setVisibility(View.INVISIBLE);
                }
                else
                {
                    mobile_error_message.setVisibility(View.VISIBLE);
                    mobile_error_message.setText("Please Insert Valid Phone Number");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        changemobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changemobile.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changemobilebtn = (TextView)findViewById(R.id.changemobilebtn);
        changemobilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               // changemobile.setEnabled(true);
                //changemobile.requestFocus();
               // changemobile.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changemobile, InputMethodManager.SHOW_IMPLICIT);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"mobile");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

        changegender = (EditText)findViewById(R.id.genderedit);
        changegender.setEnabled(false);

        gender_error_message = (TextView)findViewById(R.id.gender_error_message_textview);
        gender_error_message.setVisibility(View.INVISIBLE);

        changegender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    {
                    changegender.setEnabled(false);
                   // Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });




        changegenderbtn = (TextView)findViewById(R.id.changegenderbtn);
        changegenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               // changegender.setEnabled(true);
               // changegender.requestFocus();
               // changegender.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changegender, InputMethodManager.SHOW_IMPLICIT);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"gender");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);



            }
        });


        changenickname = (EditText)findViewById(R.id.nicknameedit);
        changenickname.setEnabled(false);

        changenickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changenickname.setEnabled(false);
                 //   Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changenicknamebtn = (TextView)findViewById(R.id.changenicknamebtn);
        changenicknamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               // changenickname.setEnabled(true);
               // changenickname.requestFocus();
               // changenickname.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changenickname, InputMethodManager.SHOW_IMPLICIT);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"nickname");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });

        changeremark = (EditText)findViewById(R.id.remarkedit);
        changeremark.setEnabled(false);

        changeremark.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changeremark.setEnabled(false);
                  //  Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeremarkbtn = (TextView)findViewById(R.id.changeremarkbtn);
        changeremarkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               // changeremark.setEnabled(true);
               // changeremark.requestFocus();
               // changeremark.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changeremark, InputMethodManager.SHOW_IMPLICIT);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"remarks");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

        changedob = (EditText)findViewById(R.id.dobedit);
        changedob.setEnabled(false);

        changedob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changedob.setEnabled(false);
                 //   Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changedobbtn = (TextView)findViewById(R.id.changedobbtn);
        changedobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
               // changedob.setEnabled(true);
               // changedob.requestFocus();
              //  changedob.setFocusable(true);
               // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changedob, InputMethodManager.SHOW_IMPLICIT);
                EditProfileDialog cdd=new EditProfileDialog(editactivity,"dob");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });

        changepin = (EditText)findViewById(R.id.pinedit);
        changepin.setEnabled(false);
        changepin.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pin_error_message = (TextView)findViewById(R.id.pin_error_message_textview);
        pin_error_message.setVisibility(View.INVISIBLE);

        changepin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changepin.setEnabled(false);
                  //  Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changepin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=6)
                {
                    pin_error_message.setVisibility(View.INVISIBLE);
                }
                else
                {
                    pin_error_message.setVisibility(View.VISIBLE);
                    pin_error_message.setText("Please 6 digit pin number");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        changepinbtn = (TextView)findViewById(R.id.changepinbtn);
        changepinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changepin.setEnabled(false);
                changepin.requestFocus();
                changepin.setFocusable(true);
                //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.showSoftInput(changepin, InputMethodManager.SHOW_IMPLICIT);

                EditProfileDialog cdd=new EditProfileDialog(editactivity,"pin");

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });










        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    public void validatealldata()
    {
        if(password_error_message.getVisibility()==View.VISIBLE||
                mobile_error_message.getVisibility()==View.VISIBLE||gender_error_message.getVisibility()==View.VISIBLE||
                pin_error_message.getVisibility()==View.VISIBLE)
        {
            Toast.makeText(this,"cant save still data wrong insert ",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } // end of TextWatcher (email)


}
