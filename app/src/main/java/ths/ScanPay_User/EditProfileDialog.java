package ths.ScanPay_User;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditProfileDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    ImageView close;
    String data;
    EditText newpasswordedit,oldpasswordedit,emailbind,newpaymentedit,oldpaymentedit;
    TextView password_error_message,emailink,payment_error_message,oldpayment_error_message;

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



        if(data.equals("password"))
        {
            setContentView(R.layout.editprofile_password_dialog);
            newpasswordedit = (EditText)findViewById(R.id.newpasswordedit);

            password_error_message = (TextView) findViewById(R.id.password_error_message_textview);
            password_error_message.setVisibility(View.INVISIBLE);

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
        if(data.equals("email"))
        {
            setContentView(R.layout.editprofile_email_dialog);
            emailbind = (EditText)findViewById(R.id.emailbind);
            emailbind.setEnabled(false);

            emailink = (TextView)findViewById(R.id.emaillink);
            emailink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","support@myscanpay.my", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    c.startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            });
        }
        if(data.equals("pin"))
        {
            setContentView(R.layout.editprofile_pin_dialog);

            payment_error_message= (TextView)findViewById(R.id.payment_error_message_textview);
            payment_error_message.setVisibility(View.INVISIBLE);

            oldpayment_error_message = (TextView)findViewById(R.id.oldpayment_error_message_textview);
            oldpayment_error_message.setVisibility(View.INVISIBLE);

            newpaymentedit = (EditText)findViewById(R.id.newpaymentedit);
            newpaymentedit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

            newpaymentedit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>=6)
                    {
                        payment_error_message.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        payment_error_message.setVisibility(View.VISIBLE);
                        payment_error_message.setText("Please 6 digit pin number");

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });



            oldpaymentedit = (EditText)findViewById(R.id.oldpaymentedit);
            oldpaymentedit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

            oldpaymentedit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()>=6)
                    {
                        oldpayment_error_message.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        oldpayment_error_message.setVisibility(View.VISIBLE);
                        oldpayment_error_message.setText("Please 6 digit pin number");

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

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
