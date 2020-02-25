package ths.ScanPay_User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import ths.ScanPay_User.GetFunction.GetUserProfileListTask;
import ths.ScanPay_User.PostFunction.PostLogout_Update_LoginID_Task;

public class SettingFragment extends Fragment {

    ImageView qr_img,aboutus,location,message,systeminfo,logout;
    public static TextView  username,ID;
    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setting_fragment, container, false);


        qr_img = (ImageView)view.findViewById(R.id.qr_img);
        aboutus = (ImageView)view.findViewById(R.id.aboutus);
        location = (ImageView)view.findViewById(R.id.location);
        message = (ImageView)view.findViewById(R.id.message) ;
        systeminfo = (ImageView)view.findViewById(R.id.systeminfo);
        logout=(ImageView)view.findViewById(R.id.logout);

        username=(TextView)view.findViewById(R.id.username);
        ID=(TextView)view.findViewById(R.id.ID);

       // new GetUserProfileListTask(getContext()).execute();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostLogout_Update_LoginID_Task(getContext()).execute(MainActivity.LoginID);
            }
        });
        qr_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getActivity(),EditProfileActivity.class);
                startActivity(lol);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getActivity(),AboutUsActivity.class);
                startActivity(lol);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getActivity(),LocationActivity.class);
                startActivity(lol);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getActivity(),MessageCentreActivity.class);
                startActivity(lol);
            }
        });
        systeminfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getActivity(),SystemInfoActivity.class);
                startActivity(lol);
            }
        });

        Glide.with(view)  //2
                .load("https://www.myscanpay.com/v4/mobile/GetQRCode.aspx?c=11111") //3

                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .override(100, 100)
                .into(qr_img) ;

        return view;
    }


}
