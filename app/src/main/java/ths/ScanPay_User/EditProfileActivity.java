package ths.ScanPay_User;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

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
