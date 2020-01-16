package ths.ScanPay_User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    ImageView back;
    ImageView profile_img;
    TextView changefullnamebtn,changeidbtn,changepasswordbtn,
             changeemailbtn,changemobilebtn,changegenderbtn,
             changenicknamebtn,changeremarkbtn,changedobbtn,
             changepinbtn;
    EditText changefullname,changeid,changepassword,changeemail,
             changemobile,changegender,changenickname,changeremark,
             changedob,changepin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        changefullname = (EditText)findViewById(R.id.fullnameedit);
        changefullname.setEnabled(false);

        changefullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changefullname.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"save full name",Toast.LENGTH_SHORT).show();

                }
            }
        });

        changefullnamebtn = (TextView)findViewById(R.id.changefullnamebtn);
        changefullnamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changefullname.setEnabled(true);
                changefullname.requestFocus();
                changefullname.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changefullname, InputMethodManager.SHOW_IMPLICIT);


            }
        });

        changeid =(EditText)findViewById(R.id.idedit);
        changeid.setEnabled(false);

        changeid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changeid.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"save id",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeidbtn = (TextView)findViewById(R.id.changeidbtn);
        changeidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changeid.setEnabled(true);
                changeid.requestFocus();
                changeid.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changeid, InputMethodManager.SHOW_IMPLICIT);


            }
        });

        changepassword = (EditText)findViewById(R.id.passwordedit);
        changepassword.setEnabled(false);

        changepassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changepassword.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changepasswordbtn = (TextView)findViewById(R.id.changepasswordbtn);
        changepasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changepassword.setEnabled(true);
                changepassword.requestFocus();
                changepassword.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changepassword, InputMethodManager.SHOW_IMPLICIT);


            }
        });

        changeemail = (EditText)findViewById(R.id.emailedit);
        changeemail.setEnabled(false);

        changeemail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    changeemail.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"save password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeemailbtn = (TextView)findViewById(R.id.changeemailbtn);
        changeemailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changefullname.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                changeemail.setEnabled(true);
                changeemail.requestFocus();
                changeemail.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changeemail, InputMethodManager.SHOW_IMPLICIT);


            }
        });

        changemobile = (EditText)findViewById(R.id.handphoneedit);
        changemobile.setEnabled(false);

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
                changemobile.setEnabled(true);
                changemobile.requestFocus();
                changemobile.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changemobile, InputMethodManager.SHOW_IMPLICIT);


            }
        });


        profile_img = (ImageView)findViewById(R.id.profile_img);
        Glide.with(this)  //2
                .load("https://simg.nicepng.com/png/small/128-1280406_view-user-icon-png-user-circle-icon-png.png") //3
                //.apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.usericon) //5
                .error(R.drawable.usericon) //6
                .into(profile_img) ;


        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }


}
