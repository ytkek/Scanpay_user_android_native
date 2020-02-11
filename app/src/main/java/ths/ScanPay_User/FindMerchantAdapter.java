package ths.ScanPay_User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FindMerchantAdapter extends RecyclerView.Adapter<FindMerchantAdapter.MyViewHolder> {

    private List<FindMerchantlist> findmerchantlist;
    private Context context;
    /**
     * View holder class
     */


    public FindMerchantAdapter(Context context, List<FindMerchantlist> findmerchantlist) {
        this.context = context;
        this.findmerchantlist = findmerchantlist;
    }

    @Override
    public void onBindViewHolder(FindMerchantAdapter.MyViewHolder holder, int position) {

        FindMerchantlist c = findmerchantlist.get(position);

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
                a.putExtra("wtf","wtf");
                a.putExtra("wtf2","wtf2");
                v.getContext().startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {

        return findmerchantlist.size();
    }

    @Override
    public FindMerchantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.findmerchant_list_row, parent, false);

        FindMerchantAdapter.MyViewHolder viewHolder = new FindMerchantAdapter.MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageView findmerchant_shopimage;
        public TextView findmerchant_shopname;
        public TextView findmerchant_shopaddress;
        public LinearLayout shoplayout;

        public MyViewHolder(View view) {
            super(view);


            findmerchant_shopimage = (ImageView) view.findViewById(R.id.shopimage);
            findmerchant_shopname = (TextView) view.findViewById(R.id.shopname);
            findmerchant_shopaddress = (TextView) view.findViewById(R.id.shopaddress);
            shoplayout = (LinearLayout)view.findViewById(R.id.shoplayout);


        }
    }

}
