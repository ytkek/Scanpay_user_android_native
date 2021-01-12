package ths.ScanPay_UserV5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_UserV5.GetFunction.GetAllDiscoveryListTask;

public class DiscoveryFragment extends Fragment {

    public static Spinner spinner;
    Button viewallbtn;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    public static boolean indicator=false;
    public DiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discovery_fragment, container, false);
        // Inflate the layout for this fragment



        spinner = (Spinner) view.findViewById(R.id.discovery_menu_list);
        List<String> list = new ArrayList<String>();
        list.add("MYSCANPAY");
        list.add("FOOD AND BEVERAGE");
        list.add("RETAIL SHOPS");
        list.add("SERVICES");
        list.add("NEW HIRING");
        list.add("MOBILE TRUCK");
        list.add("OTHER GROUPS");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new GetAllDiscoveryListTask(MainActivity.mainactivity,recyclerView).execute(MainActivity.LoginID,MainActivity.Password);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewallbtn = (Button) view.findViewById(R.id.viewallbtn);

        viewallbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               indicator=true;
                new GetAllDiscoveryListTask(MainActivity.mainactivity,recyclerView).execute(MainActivity.LoginID,MainActivity.Password);
            }

        });

        recyclerView = (RecyclerView) view.findViewById(R.id.discovery_menu_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        new GetAllDiscoveryListTask(MainActivity.mainactivity,recyclerView).execute(MainActivity.LoginID,MainActivity.Password);


        return view;
    }



}

