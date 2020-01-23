package ths.ScanPay_User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView balanceimagebtn;
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
        // Inflate the layout for this fragment

        balanceimagebtn = (ImageView)view.findViewById(R.id.balancebtn);
        balanceimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lol = new Intent(getContext(),BalanceActivity.class);
                startActivity(lol);
            }
        });
        return view;
    }


}
