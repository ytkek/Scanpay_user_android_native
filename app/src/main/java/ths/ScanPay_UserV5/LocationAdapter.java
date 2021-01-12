package ths.ScanPay_UserV5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        final Locationlist c = locationList.get(position);

        Glide.with(context)  //2
                .load(c.getM_topup()) //3
                .fitCenter()
                .into(holder.topup);

        Glide.with(context)  //2
                .load(ApiUrl.PicDomain+c.getM_profileimagepath()+c.getM_profilefilename()) //3
                .fitCenter()
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(holder.findmerchant_shopimage);

        holder.findmerchant_shopname.setText(c.getM_companyname());
        holder.findmerchant_shopaddress.setText(c.getM_address1()+c.getM_address2()+c.getM_address3());
        holder.shoplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(v.getContext(),FindMerchantDetail.class);
                a.putExtra("m_companyname",c.getM_companyname());
                a.putExtra("m_address",c.getM_address1()+c.getM_address2()+c.getM_address3()+c.getM_address4());
                a.putExtra("m_tel",c.getM_telcc()+c.getM_telac()+c.getM_telno());
                a.putExtra("m_mobileno",c.getM_mobileno());
                a.putExtra("m_email",c.getM_email());
                a.putExtra("m_url",c.getM_url());
                a.putExtra("m_topup",c.getM_topup());
                a.putExtra("m_businesshour",c.getM_businesshour());
                a.putExtra("m_remarks",c.getM_remarks());
                a.putExtra("m_profilepic",ApiUrl.PicDomain+c.getM_profileimagepath()+c.getM_profilefilename());
                a.putExtra("m_photofilename1",ApiUrl.PicDomain+c.getM_profileimagepath()+c.getM_photofilename1());
                a.putExtra("m_photofilename2",ApiUrl.PicDomain+c.getM_profileimagepath()+c.getM_photofilename2());
                a.putExtra("m_photofilename3",ApiUrl.PicDomain+c.getM_profileimagepath()+c.getM_photofilename3());
                v.getContext().startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {

        return locationList.size();
    }

    @Override
    public LocationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.findmerchant_list_row, parent, false);

        LocationAdapter.MyViewHolder viewHolder = new LocationAdapter.MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView findmerchant_shopimage,topup;
        public TextView findmerchant_shopname;
        public TextView findmerchant_shopaddress;
        public LinearLayout shoplayout;

        public MyViewHolder(View view) {
            super(view);


            findmerchant_shopimage = (ImageView) view.findViewById(R.id.shopimage);
            topup=(ImageView)view.findViewById(R.id.topup);
            findmerchant_shopname = (TextView) view.findViewById(R.id.shopname);
            findmerchant_shopaddress = (TextView) view.findViewById(R.id.shopaddress);
            shoplayout = (LinearLayout)view.findViewById(R.id.shoplayout);


        }
    }

}