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

public class MessageCentreAdapter extends RecyclerView.Adapter<MessageCentreAdapter.MyViewHolder> {

    private List<MessageCentrelist> messagecentreList;
    private Context context;
    /**
     * View holder class
     */


    public MessageCentreAdapter(Context context, List<MessageCentrelist> messagecentreList) {
        this.context = context;
        this.messagecentreList = messagecentreList;
    }

    @Override
    public void onBindViewHolder(MessageCentreAdapter.MyViewHolder holder, int position) {

        MessageCentrelist c = messagecentreList.get(position);


        holder.messagecentre_title.setText(c.getTitle());
        holder.messagecentre_message.setText(c.getMessage());
    }

    @Override
    public int getItemCount() {

        return messagecentreList.size();
    }

    @Override
    public MessageCentreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagcentre_list_row, parent, false);

        MessageCentreAdapter.MyViewHolder viewHolder = new MessageCentreAdapter.MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView messagecentre_title;
        public TextView messagecentre_message;


        public MyViewHolder(View view) {
            super(view);


            messagecentre_title = (TextView) view.findViewById(R.id.title);
            messagecentre_message = (TextView) view.findViewById(R.id.message);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, String.valueOf(getLayoutPosition()),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
