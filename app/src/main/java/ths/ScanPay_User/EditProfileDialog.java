package ths.ScanPay_User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ths.ScanPay_User.GetFunction.GetUserProfileListTask;
import ths.ScanPay_User.PostFunction.PostUserProfile_Dob_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_Gender_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_MobileNumber_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_Name_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_NewPassword_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_NickName_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_Pin_Payment_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_Recover_Pin_Task;
import ths.ScanPay_User.PostFunction.PostUserProfile_Remarks_Task;

public class EditProfileDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no,savebtn,savebtn_password,forgotpin_btn;
    ImageView close,newpayment_image_btn,oldpayment_image_btn;
    String data;
    EditText newpasswordedit,oldpasswordedit,emailbind,newpaymentedit,oldpaymentedit,fullnameedit,mobilenumberedit,genderedit,nicknameedit,remarkedit;
    TextView oldpassword_error_message,password_error_message,emailink,payment_error_message,oldpayment_error_message,
            fullname_error_message,mobilenumber_error_message,gender_error_message,nickname_error_message,remark_error_message,
            dob_error_message;
    public static TextView forgetpin_error_message_textview;
    public static EditText dobedit;

    EditText pin1,pin2,pin3,pin4,pin5,pin6,oldpin1,oldpin2,oldpin3,oldpin4,oldpin5,oldpin6;
    public boolean indicator_newpin,indicator_oldpin,indicator_newpayment_image_btn,indicator_oldpayment_image_btn;
    public EditProfileDialog(Activity a,String data) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.data=data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(data.equals("fullname"))
        {
            setContentView(R.layout.editprofile_fullname_dialog);
            fullnameedit = (EditText)findViewById(R.id.fullnameedit);
            fullnameedit.setText(GlobalVariable.OldName);
            fullname_error_message = (TextView)findViewById(R.id.fullname_error_message_textview);
            fullname_error_message.setVisibility(View.INVISIBLE);

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   dismiss();
                   new PostUserProfile_Name_Task(c).execute(fullnameedit.getText().toString(),MainActivity.LoginID);
                }
            });
        }

        if(data.equals("password"))
        {
            setContentView(R.layout.editprofile_password_dialog);
            password_error_message = (TextView) findViewById(R.id.password_error_message_textview);
            password_error_message.setVisibility(View.INVISIBLE);

            oldpassword_error_message = (TextView) findViewById(R.id.oldpassword_error_message_textview);
            oldpassword_error_message.setVisibility(View.INVISIBLE);

            newpasswordedit = (EditText)findViewById(R.id.newpasswordedit);
            oldpasswordedit = (EditText)findViewById(R.id.oldpasswordedit);

            savebtn_password=(Button)findViewById(R.id.savebtn);
            savebtn_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(oldpasswordedit.getText().toString().equals(GlobalVariable.OldPassword))
                    {
                        oldpassword_error_message.setVisibility(View.INVISIBLE);
                        if(! newpasswordedit.getText().toString().equals("") && password_error_message.getVisibility()==View.INVISIBLE)
                        {
                            oldpassword_error_message.setVisibility(View.INVISIBLE);
                            dismiss();
                            new PostUserProfile_NewPassword_Task(c).execute(MainActivity.LoginID,newpasswordedit.getText().toString());
                        }
                        else
                        {
                            oldpassword_error_message.setVisibility(View.VISIBLE);
                            oldpassword_error_message.setText("Still Exits Error Message");
                        }

                    }
                    else
                    {
                        oldpassword_error_message.setVisibility(View.VISIBLE);
                        oldpassword_error_message.setText("Type in Wrong Old Password");
                    }
                }
            });


            newpasswordedit.addTextChangedListener(new TextWatcher() {
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
        }

        if(data.equals("mobile"))
        {
            setContentView(R.layout.editprofile_mobile_dialog);
            mobilenumberedit = (EditText)findViewById(R.id.mobilenumberedit);
            mobilenumberedit.setText(GlobalVariable.OldMobileNumber);
            mobilenumber_error_message = (TextView)findViewById(R.id.mobilenumber_error_message_textview);
            mobilenumber_error_message.setVisibility(View.INVISIBLE);

            mobilenumberedit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().startsWith("0"))
                    {


                        mobilenumberedit.setText("");
                    }

                    if(s.length()==8||s.length()==9||s.length()==10)
                    {
                        mobilenumber_error_message.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        mobilenumber_error_message.setVisibility(View.VISIBLE);
                        mobilenumber_error_message.setText("Please Insert Valid Phone Number");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });





            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    new PostUserProfile_MobileNumber_Task(c).execute(MainActivity.LoginID,mobilenumberedit.getText().toString());
                }
            });
        }
        if(data.equals("email"))
        {
            setContentView(R.layout.editprofile_email_dialog);
            emailbind = (EditText)findViewById(R.id.emailbind);
            emailbind.setEnabled(false);
            emailbind.setText(GlobalVariable.OldEmail);
            emailink = (TextView)findViewById(R.id.emaillink);
            emailink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","support@myscanpay.my", null));
                    emailIntent.putExtra(Intent.EXTRA_EMAIL,GlobalVariable.OldEmail);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    c.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            });
        }

        if(data.equals("gender"))
        {
            setContentView(R.layout.editprofile_gender_dialog);

            gender_error_message= (TextView)findViewById(R.id.gender_error_message_textview);
            gender_error_message.setVisibility(View.INVISIBLE);

            genderedit = (EditText)findViewById(R.id.genderedit);
            genderedit.setText(GlobalVariable.OldGender);
            genderedit.setFocusable(false);
            genderedit.setClickable(true);
            genderedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  EditProfileGenderDialog cdd=new EditProfileGenderDialog(c);

                   // cdd.show();
                  //  Window window = cdd.getWindow();
                  //  window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Please choose your gender");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton("Male", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            genderedit.setText("male");
                        }
                    });
                    builder1.setNegativeButton("Female", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            genderedit.setText("female");
                        }
                    });

                    AlertDialog alertDialog = builder1.create();
                    alertDialog.show();
                }
            });
            genderedit.addTextChangedListener(new TextWatcher() {{

               // EditProfileDialog cdd=new EditProfileDialog(c,"gender");

               // cdd.show();
             //   Window window = cdd.getWindow();
              //  window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().toLowerCase().equals("male")||s.toString().toLowerCase().equals("female"))
                    {
                        gender_error_message.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        gender_error_message.setVisibility(View.VISIBLE);
                        gender_error_message.setText("Please insert male or female only");

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!genderedit.getText().toString().equals("")&& gender_error_message.getVisibility()==View.INVISIBLE)
                    {
                        gender_error_message.setVisibility(View.INVISIBLE);
                        dismiss();
                        new PostUserProfile_Gender_Task(c).execute(MainActivity.LoginID,genderedit.getText().toString());
                    }
                    else
                    {
                        gender_error_message.setVisibility(View.VISIBLE);
                        gender_error_message.setText("Still Exits Error Message");
                    }

                }
            });
        }

        if(data.equals("nickname"))
        {
            setContentView(R.layout.editprofile_nickname_dialog);
            nicknameedit = (EditText)findViewById(R.id.nicknameedit);
            nicknameedit.setText(GlobalVariable.OldNickName);
            nicknameedit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                   if(s.length()>0)
                   {
                       nickname_error_message.setVisibility(View.INVISIBLE);
                   }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            nickname_error_message=(TextView)findViewById(R.id.nickname_error_message_textview);
            nickname_error_message.setVisibility(View.INVISIBLE);

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!nicknameedit.getText().toString().equals("")&& nickname_error_message.getVisibility()==View.INVISIBLE)
                    {
                        nickname_error_message.setVisibility(View.INVISIBLE);
                        dismiss();
                        new PostUserProfile_NickName_Task(c).execute(nicknameedit.getText().toString(),MainActivity.LoginID);
                    }
                    else
                    {
                        nickname_error_message.setVisibility(View.VISIBLE);
                        nickname_error_message.setText("Still Exits Error Message");
                    }

                }
            });
        }

        if(data.equals("remarks"))
        {
            setContentView(R.layout.editprofile_remarks_dialog);



            remarkedit = (EditText)findViewById(R.id.remarksedit);
            remarkedit.setText(GlobalVariable.OldRemarks);
            remarkedit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>0)
                    {
                        remark_error_message.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            remark_error_message=(TextView)findViewById(R.id.remarks_error_message_textview);
            remark_error_message.setVisibility(View.INVISIBLE);

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!remarkedit.getText().toString().equals("")&& remark_error_message.getVisibility()==View.INVISIBLE)
                    {
                        remark_error_message.setVisibility(View.INVISIBLE);
                        dismiss();
                        new PostUserProfile_Remarks_Task(c).execute(remarkedit.getText().toString(),MainActivity.LoginID);
                    }
                    else
                    {
                        remark_error_message.setVisibility(View.VISIBLE);
                        remark_error_message.setText("Still Exits Error Message");
                    }

                }
            });
        }

        if(data.equals("dob"))
        {
            setContentView(R.layout.editprofile_dob_dialog);

            dob_error_message = (TextView)findViewById(R.id.dob_error_message_textview);
            dob_error_message.setVisibility(View.INVISIBLE);

            dobedit = (EditText)findViewById(R.id.dobedit);
            dobedit.setText(GlobalVariable.OldDob);
            dobedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if(hasFocus==true)
                    {
                        DateDialog cdd=new DateDialog(c,"dob");

                        cdd.show();
                        Window window = cdd.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    }

                }
            });

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!dobedit.getText().toString().equals("")&&dob_error_message.getVisibility()==View.INVISIBLE)
                    {
                        dob_error_message.setVisibility(View.INVISIBLE);
                        dismiss();
                        new PostUserProfile_Dob_Task(c).execute(dobedit.getText().toString(),MainActivity.LoginID);
                    }
                    else
                    {
                        dob_error_message.setVisibility(View.VISIBLE);
                        dob_error_message.setText("Still Exits Error Message");
                    }

                }
            });

        }
        if(data.equals("pin"))
        {
            setContentView(R.layout.editprofile_pin_dialog);

            Toast.makeText(c,GlobalVariable.OldPin,Toast.LENGTH_SHORT).show();

            forgetpin_error_message_textview=(TextView)findViewById(R.id.forgetpin_error_message_textview);
            forgetpin_error_message_textview.setVisibility(View.INVISIBLE);

            forgotpin_btn = (Button)findViewById(R.id.forgotbtn);

            forgotpin_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PostUserProfile_Recover_Pin_Task(c).execute(GlobalVariable.OldPin,GlobalVariable.OldEmail,GlobalVariable.OldName);
                }
            });
            payment_error_message= (TextView)findViewById(R.id.payment_error_message_textview);
            payment_error_message.setVisibility(View.INVISIBLE);

            oldpayment_error_message = (TextView)findViewById(R.id.oldpayment_error_message_textview);
            oldpayment_error_message.setVisibility(View.INVISIBLE);

            pin1 = (EditText) findViewById(R.id.pin1);
            pin2 = (EditText) findViewById(R.id.pin2);
            pin3 = (EditText) findViewById(R.id.pin3);
            pin4 = (EditText) findViewById(R.id.pin4);
            pin5 = (EditText) findViewById(R.id.pin5);
            pin6 = (EditText) findViewById(R.id.pin6);
            oldpin1 = (EditText) findViewById(R.id.oldpin1);
            oldpin2 = (EditText) findViewById(R.id.oldpin2);
            oldpin3 = (EditText) findViewById(R.id.oldpin3);
            oldpin4 = (EditText) findViewById(R.id.oldpin4);
            oldpin5 = (EditText) findViewById(R.id.oldpin5);
            oldpin6 = (EditText) findViewById(R.id.oldpin6);


            newpayment_image_btn=(ImageView)findViewById(R.id.newpayment_image_btn);
            newpayment_image_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(indicator_newpayment_image_btn==false)
                    {
                        newpayment_image_btn.setBackgroundResource(R.drawable.open_eye);
                        indicator_newpayment_image_btn=true;
                        pin1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pin2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pin3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pin4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pin5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pin6.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                    }
                    else if (indicator_newpayment_image_btn==true)
                    {
                        newpayment_image_btn.setBackgroundResource(R.drawable.close_eye);
                        indicator_newpayment_image_btn=false;
                        pin1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pin2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pin3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pin4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pin5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pin6.setTransformationMethod(PasswordTransformationMethod.getInstance());


                    }

                }
            });
            oldpayment_image_btn=(ImageView)findViewById(R.id.oldpayment_image_btn);
            oldpayment_image_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(indicator_oldpayment_image_btn==false)
                    {
                        oldpayment_image_btn.setBackgroundResource(R.drawable.open_eye);
                        indicator_oldpayment_image_btn=true;
                        oldpin1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpin2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpin3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpin4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpin5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpin6.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                    }
                    else if (indicator_oldpayment_image_btn==true)
                    {
                        oldpayment_image_btn.setBackgroundResource(R.drawable.close_eye);
                        indicator_oldpayment_image_btn=false;
                        oldpin1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpin2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpin3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpin4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpin5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpin6.setTransformationMethod(PasswordTransformationMethod.getInstance());


                    }

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

                    }
                }
            });
            pin6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length()!=0)
                    {


                    }

                }
                @Override
                public void afterTextChanged(Editable s) {

                }

            });
            pin1.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspac

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

                            pin5.requestFocus();
                        }

                    }
                    return false;
                }
            });
            oldpin1.addTextChangedListener(new TextWatcher() {
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
                        oldpin2.requestFocus();

                    }
                }
            });
            oldpin2.addTextChangedListener(new TextWatcher() {
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
                        oldpin3.requestFocus();

                    }

                }
            });
            oldpin3.addTextChangedListener(new TextWatcher() {
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
                        oldpin4.requestFocus();

                    }
                }
            });

            oldpin4.addTextChangedListener(new TextWatcher() {
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
                        oldpin5.requestFocus();

                    }

                }
            });

            oldpin5.addTextChangedListener(new TextWatcher() {
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
                        oldpin6.requestFocus();

                    }

                }
            });
            oldpin6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length()!=0)
                    {


                    }

                }
                @Override
                public void afterTextChanged(Editable s) {

                }

            });
            oldpin1.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspac

                    }
                    return false;
                }
            });
            oldpin2.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspace
                        if(oldpin2.getText().toString().equals(""))
                        {

                            oldpin1.requestFocus();
                        }

                    }
                    return false;
                }
            });
            oldpin3.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspace
                        if(oldpin3.getText().toString().equals(""))
                        {

                            oldpin2.requestFocus();
                        }

                    }
                    return false;
                }
            });

            oldpin4.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspace
                        if(oldpin4.getText().toString().equals(""))
                        {

                            oldpin3.requestFocus();
                        }

                    }
                    return false;
                }
            });

            oldpin5.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspace
                        if(oldpin5.getText().toString().equals(""))
                        {

                            oldpin4.requestFocus();
                        }

                    }
                    return false;
                }
            });
            oldpin6.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        //this is for backspace
                        if(oldpin6.getText().toString().equals(""))
                        {

                            oldpin5.requestFocus();
                        }

                    }
                    return false;
                }
            });

            savebtn = (Button)findViewById(R.id.savebtn);
            savebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!pin1.getText().toString().equals("")&&!pin2.getText().toString().equals("")&&!pin3.getText().toString().equals("")&&!pin4.getText().toString().equals("")&&!pin5.getText().toString().equals("")&&!pin6.getText().toString().equals(""))
                    {
                        payment_error_message.setVisibility(View.INVISIBLE);
                       // dismiss();
                       // new PostUserProfile_Dob_Task(c).execute(dobedit.getText().toString(),"601121829875");
                        indicator_newpin=true;
                        if(indicator_newpin==true&&indicator_oldpin==true)
                        {
                            //Toast.makeText(c,"save success",Toast.LENGTH_SHORT).show();
                            new PostUserProfile_Pin_Payment_Task(c).execute(MainActivity.LoginID,pin1.getText().toString()+pin2.getText().toString()+pin3.getText().toString()+ pin4.getText().toString()+pin5.getText().toString()+pin6.getText().toString());
                            indicator_oldpin=false;
                            indicator_newpin=false;
                        }
                    }
                    else
                    {
                        payment_error_message.setVisibility(View.VISIBLE);
                        payment_error_message.setText("Please fill all the new payment pin");
                    }

                    if(!oldpin1.getText().toString().equals("")&&!oldpin2.getText().toString().equals("")&&!oldpin3.getText().toString().equals("")&&!oldpin4.getText().toString().equals("")&&!oldpin5.getText().toString().equals("")&&!oldpin6.getText().toString().equals(""))
                    {

                        oldpayment_error_message.setVisibility(View.INVISIBLE);
                        if(GlobalVariable.OldPin.equals(oldpin1.getText().toString()+oldpin2.getText().toString()+oldpin3.getText().toString()+oldpin4.getText().toString()+oldpin5.getText().toString()+oldpin6.getText().toString()))
                        {
                            indicator_oldpin=true;
                            if(indicator_newpin==true&&indicator_oldpin==true)
                            {
                                new PostUserProfile_Pin_Payment_Task(c).execute(MainActivity.LoginID,pin1.getText().toString()+pin2.getText().toString()+pin3.getText().toString()+ pin4.getText().toString()+pin5.getText().toString()+pin6.getText().toString());
                                //Toast.makeText(c,"save success",Toast.LENGTH_SHORT).show();
                                indicator_oldpin=false;
                                indicator_newpin=false;
                            }
                        }
                        else
                        {
                            oldpayment_error_message.setVisibility(View.VISIBLE);
                            oldpayment_error_message.setText("Old payment pin not match");
                        }

                    }
                    else
                    {
                        oldpayment_error_message.setVisibility(View.VISIBLE);
                        oldpayment_error_message.setText("Please fill all the old payment pin");
                    }

                }
            });

        }

        close = (ImageView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
       // yes = (Button) findViewById(R.id.btn_yes);
       // no = (Button) findViewById(R.id.btn_no);
       // yes.setOnClickListener(this);
       // no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
