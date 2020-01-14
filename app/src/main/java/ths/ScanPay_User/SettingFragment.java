package ths.ScanPay_User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class SettingFragment extends Fragment {

    ImageView qr_img;
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


        Glide.with(view)  //2
                .load("https://www.myscanpay.com/v4/mobile/GetQRCode.aspx?c=11111") //3

                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .override(100, 100)
                .into(qr_img) ;

        return view;
    }


}
