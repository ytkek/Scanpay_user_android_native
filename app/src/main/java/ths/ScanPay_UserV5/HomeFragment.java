package ths.ScanPay_UserV5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView balanceimagebtn,jompaybtn,findmerchantbtn,paybtn,topupbtn,recommendbtn,voucherbtn,rewardbtn;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        topupbtn = (ImageView)view.findViewById(R.id.topupbtn);
        topupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getContext(),TopUpScanQRActivity.class);
                startActivity(lol);
            }
        });
        paybtn = (ImageView)view.findViewById(R.id.paybtn);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getContext(),PaymentScanQRActivity.class);
                startActivity(lol);
            }
        });
        jompaybtn = (ImageView)view.findViewById(R.id.jompaybtn);
        jompaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getContext(),JomPayWebview.class);
                startActivity(lol);
            }
        });
        balanceimagebtn = (ImageView)view.findViewById(R.id.balancebtn);
        balanceimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lol = new Intent(getContext(),BalanceActivity.class);
                startActivity(lol);
            }
        });

        findmerchantbtn = (ImageView)view.findViewById(R.id.findmerchantbtn);
        findmerchantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(getContext(),FindMerchantActivity.class);
                startActivity(lol);
            }
        });

        recommendbtn = (ImageView)view.findViewById(R.id.recommend_btn);
        recommendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Under Progress")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        rewardbtn= (ImageView)view.findViewById(R.id.reward_btn);
        rewardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Under Progress")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        voucherbtn = (ImageView)view.findViewById(R.id.voucher_btn);
        voucherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Under Progress")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return view;
    }


}
