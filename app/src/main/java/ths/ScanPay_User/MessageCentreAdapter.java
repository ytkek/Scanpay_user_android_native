package ths.ScanPay_User;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_User.MessageNotification.MessageNotification;

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
    public void onBindViewHolder(MessageCentreAdapter.MyViewHolder holder, final int position) {

        final MessageCentrelist c = messagecentreList.get(position);


            if(MessageCentreActivity.db.getAllMessage().get(position).getIndicator().equals("false"))
            {
                holder.indicator.setVisibility(View.VISIBLE);
                Log.d("wtf",""+MessageCentreActivity.db.getAllMessage().get(position).getIndicator());
            }
            else if (MessageCentreActivity.db.getAllMessage().get(position).getIndicator().equals("true"))
            {
                holder.indicator.setVisibility(View.INVISIBLE);
                Log.d("wtf",""+MessageCentreActivity.db.getAllMessage().get(position).getIndicator());
            }


            // MessageCentreActivity.db.deleteNote(MessageCentreActivity.db.getAllMessage().get(i));


        holder.messagecentre_title.setText(c.getTitle());
        holder.messagecentre_message.setText(c.getMessage());

        holder.message_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageCentreActivity.db.updateNote("true",position+1);

                Intent a = new Intent(v.getContext(),MessageCentreDetail.class);
                a.putExtra("nob_id",c.getId());
                a.putExtra("nob_title",c.getTitle());
                a.putExtra("nob_message",c.getMessage());
                a.putExtra("nob_publishdate",c.getDate());

                v.getContext().startActivity(a);
            }
        });
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
        public LinearLayout message_layout;
        public ImageView indicator;


        public MyViewHolder(View view) {
            super(view);


            messagecentre_title = (TextView) view.findViewById(R.id.title);
            messagecentre_message = (TextView) view.findViewById(R.id.message);
            indicator = (ImageView)view.findViewById(R.id.indicator);
            indicator.setVisibility(View.INVISIBLE);
            message_layout= (LinearLayout)view.findViewById(R.id.messagelayout);

        }
    }
}
