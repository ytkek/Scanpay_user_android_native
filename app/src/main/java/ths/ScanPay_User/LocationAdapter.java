package ths.ScanPay_User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<Locationlist> locationList;
    private Context context;

    /**
     * View holder class
     */


    public LocationAdapter(Context context, List<Locationlist> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @Override
    public void onBindViewHolder(LocationAdapter.MyViewHolder holder, int position) {

        Locationlist c = locationList.get(position);

        Glide.with(context)  //2
                .load(c.getLocation_img()) //3
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(holder.location_img);
        holder.location_companyname.setText(c.getLocation_companyname());
        holder.location_address.setText(c.getLocation_address());
        Glide.with(context)  //2
                .load(c.getLocation_img()) //3
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(holder.location_topup);
    }

    @Override
    public int getItemCount() {

        return locationList.size();
    }

    @Override
    public LocationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_row, parent, false);

        LocationAdapter.MyViewHolder viewHolder = new LocationAdapter.MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView location_img;
        public TextView location_companyname;
        public TextView location_address;
        public ImageView location_topup;


        public MyViewHolder(View view) {
            super(view);

            location_img = (ImageView) view.findViewById(R.id.location_img);
            location_companyname = (TextView) view.findViewById(R.id.location_companyname);
            location_address = (TextView) view.findViewById(R.id.location_address);
            location_topup = (ImageView) view.findViewById(R.id.location_topup);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, String.valueOf(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}