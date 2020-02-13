package ths.ScanPay_User;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jsibbold.zoomage.ZoomageView;

public class FindMerchantDetail extends AppCompatActivity {

    public String m_companyname;
    public String m_address;
    public String m_tel;
    public String m_mobileno;
    public String m_email;
    public String m_url;
    public String m_businesshour;
    public String m_remarks;
    public String m_profilepic;
    public String m_photofilename1;
    public String m_photofilename2;
    public String m_photofilename3;
    public ImageView profilepic,photofilename1,photofilename2,photofilename3,back,location,hp,email,website;
    public TextView companyname,time,information;
    Activity findmerchantdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findmerchant_detail);

        findmerchantdetail=this;
        Intent intent = getIntent();

        m_companyname = intent.getStringExtra("m_companyname");
        m_address=intent.getStringExtra("m_address");
        m_tel=intent.getStringExtra("m_tel");
        m_mobileno = intent.getStringExtra("m_mobileno");
        m_email=intent.getStringExtra("m_email");
        m_url=intent.getStringExtra("m_url");
        m_businesshour = intent.getStringExtra("m_businesshour");
        m_remarks = intent.getStringExtra("m_remarks");
        m_profilepic=intent.getStringExtra("m_profilepic");
        m_photofilename1 = intent.getStringExtra("m_photofilename1");
        m_photofilename2 = intent.getStringExtra("m_photofilename2");
        m_photofilename3 = intent.getStringExtra("m_photofilename3");


        back = (ImageView) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        profilepic = (ImageView)findViewById(R.id.profilepic);
        Glide.with(this)  //2
                .load(m_profilepic) //3
                .fitCenter()
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(profilepic);

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(findmerchantdetail, R.style.DialogFullScreenTheme);

                View v2 = findmerchantdetail.getLayoutInflater().inflate(R.layout.image_dialog_layout, null);

                ZoomageView imageZoom = (ZoomageView)v2.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) profilepic.getDrawable()).getBitmap();
                imageZoom.setImageBitmap(bm);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                settingsDialog.setContentView(v2);
                settingsDialog.show();
            }
        });
        companyname = (TextView)findViewById(R.id.companyname);
        companyname.setText(m_companyname);

        photofilename1 = (ImageView)findViewById(R.id.photoprofilepic1);
        Glide.with(this)  //2
                .load(m_photofilename1) //3
                .fitCenter()
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(photofilename1);
        photofilename1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(findmerchantdetail, R.style.DialogFullScreenTheme);

                View v2 = findmerchantdetail.getLayoutInflater().inflate(R.layout.image_dialog_layout, null);

                ZoomageView imageZoom = (ZoomageView)v2.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) photofilename1.getDrawable()).getBitmap();
                imageZoom.setImageBitmap(bm);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                settingsDialog.setContentView(v2);
                settingsDialog.show();
            }
        });
        photofilename2 = (ImageView)findViewById(R.id.photoprofilepic2);
        Glide.with(this)  //2
                .load(m_photofilename2) //3
                .fitCenter()
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(photofilename2);
        photofilename2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(findmerchantdetail, R.style.DialogFullScreenTheme);

                View v2 = findmerchantdetail.getLayoutInflater().inflate(R.layout.image_dialog_layout, null);

                ZoomageView imageZoom = (ZoomageView)v2.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) photofilename2.getDrawable()).getBitmap();
                imageZoom.setImageBitmap(bm);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                settingsDialog.setContentView(v2);
                settingsDialog.show();
            }
        });
        photofilename3 = (ImageView)findViewById(R.id.photoprofilepic3);
        Glide.with(this)  //2
                .load(m_photofilename3) //3
                .fitCenter()
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(photofilename3);

        photofilename3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(findmerchantdetail, R.style.DialogFullScreenTheme);

                View v2 = findmerchantdetail.getLayoutInflater().inflate(R.layout.image_dialog_layout, null);

                ZoomageView imageZoom = (ZoomageView)v2.findViewById(R.id.profile_img_popup);
                Bitmap bm = ((BitmapDrawable) photofilename3.getDrawable()).getBitmap();
                imageZoom.setImageBitmap(bm);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                settingsDialog.setContentView(v2);
                settingsDialog.show();
            }
        });

        time= (TextView)findViewById(R.id.time);
        time.setText(m_businesshour);



        information = (TextView)findViewById(R.id.information);
        information.setText(m_remarks);

        location=(ImageView)findViewById(R.id.navigation);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" + m_address));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        hp=(ImageView)findViewById(R.id.phone);
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+m_mobileno));
                startActivity(intent);
            }
        });

        email = (ImageView)findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",m_email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, ""));
            }
        });

        website=(ImageView)findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = m_url;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}