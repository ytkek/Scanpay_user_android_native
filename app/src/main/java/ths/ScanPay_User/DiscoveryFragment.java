package ths.ScanPay_User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.GetFunction.GetAllDiscoveryListTask;

public class DiscoveryFragment extends Fragment {

    Spinner spinner;
    Button viewallbtn;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
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


        viewallbtn = (Button) view.findViewById(R.id.viewallbtn);

        viewallbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner.getSelectedItem()),

                        Toast.LENGTH_SHORT).show();
            }

        });

        recyclerView = (RecyclerView) view.findViewById(R.id.discovery_menu_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        new GetAllDiscoveryListTask(getContext(),recyclerView).execute();
       // List<Discoverylist> discoverylist = new ArrayList<>();
      //  discoverylist.add(new Discoverylist("https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png","SCANPAY","SCANPAY ON SALES"));
      //  discoverylist.add(new Discoverylist("https://f0.pngfuel.com/png/408/791/sticker-sales-hot-price-fire-sticker-white-hot-price-text-with-red-flame-background-illustration-png-clip-art-thumbnail.png","SCANPAY","SCANPAY ON SALES"));


        return view;
    }



}

