package ths.ScanPay_User;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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

        final Discoverylist c = discoveryList.get(position);

        Glide.with(context)  //2
                .load(ApiUrl.PicDomain+c.getDiscovery_imagepath()+c.getDiscovery_image()) //3
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_broken) //6
                .into(holder.discovery_img) ;
        holder.discovery_title.setText(c.getDiscovery_name());
       holder.discovery_content.setText(c.getDiscovery_description());
        holder.discovery_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(c.getDiscovery_externallink()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
        holder.discovery_share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ScanPay Advertisement");
                    String shareMessage= "ScanPay help you to share\n";
                    shareMessage = shareMessage  + c.getDiscovery_externallink() +"";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });


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
        public LinearLayout discovery_share_btn;
        public LinearLayout discovery_more_btn;


        public MyViewHolder(View view) {
            super(view);

            discovery_img = (ImageView) view.findViewById(R.id.discovery_img);
            discovery_title = (TextView) view.findViewById(R.id.discovery_title);
            discovery_content = (TextView) view.findViewById(R.id.discovery_content);
            discovery_share_btn = (LinearLayout) view.findViewById(R.id.share_btn);
            discovery_more_btn = (LinearLayout)view.findViewById(R.id.more_btn);
            discovery_share_btn = (LinearLayout)view.findViewById(R.id.share_btn);

        }
    }
}
