package ths.ScanPay_UserV5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.util.HttpUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.Generic.GenericAutologin;
import ths.ScanPay_UserV5.GetFunction.GetUserProfile_Name_ID_Task;
import ths.ScanPay_UserV5.PostFunction.PostLogout_Update_LoginID_Task;

public class SettingFragment extends Fragment {

    ImageView qr_img,aboutus,location,message,systeminfo,logout;
    public static TextView  username,ID;
    public String encryptedString;
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

        new GetUserProfile_Name_ID_Task(MainActivity.mainactivity).execute(MainActivity.LoginID,MainActivity.Password);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostLogout_Update_LoginID_Task(MainActivity.mainactivity).execute(MainActivity.LoginID);

                GenericAutologin.clearall(getContext());
                FirebaseMessaging.getInstance().unsubscribeFromTopic("all");
                FirebaseMessaging.getInstance().unsubscribeFromTopic(MainActivity.LoginID);
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

        try {
            encryptedString = Encrypt(MainActivity.LoginID + "+" + MainActivity.Password, "@McQfTjWnZq4t7w!");

            Log.e("AES",URLEncoder.encode(encryptedString));

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        Glide.with(view)  //2
                .load("https://www.myscanpay.com/V5/mobile/GetQRCode.aspx?c="+MainActivity.LoginID+"&Token="+ URLEncoder.encode(encryptedString)) //3

                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .override(100, 100)
                .into(qr_img) ;

        return view;
    }
    public static String Encrypt(String text,String key)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;

        if (len > keyBytes.length)
            len = keyBytes.length;

        System.arraycopy(b,0,keyBytes,0,len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));




        return Base64.encodeToString(results,Base64.DEFAULT);
    }


}
