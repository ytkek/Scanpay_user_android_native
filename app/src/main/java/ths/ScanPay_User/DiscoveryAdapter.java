package ths.ScanPay_User;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DiscoveryAdapter  extends RecyclerView.Adapter<DiscoveryAdapter.MyViewHolder> {

    private List<Discoverylist> discoveryList;
    private Context context;
    /**
     * View holder class
     */


    public DiscoveryAdapter(Context context, List<Discoverylist> discoveryList) {
        this.context = context;
        this.discoveryList = discoveryList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Discoverylist c = discoveryList.get(position);

        Glide.with(context)  //2
                .load(c.getDiscovery_img()) //3
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(holder.discovery_img) ;
        holder.discovery_title.setText(c.getDiscovery_title());
       holder.discovery_content.setText(c.getDiscovery_content());
    }

    @Override
    public int getItemCount() {

        return discoveryList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discovery_list_row, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView discovery_img;
        public TextView discovery_title;
        public TextView discovery_content;


        public MyViewHolder(View view) {
            super(view);

            discovery_img = (ImageView) view.findViewById(R.id.discovery_img);
            discovery_title = (TextView) view.findViewById(R.id.discovery_title);
            discovery_content = (TextView) view.findViewById(R.id.discovery_content);
        }
    }
}
