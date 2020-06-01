package ths.ScanPay_User;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.MyViewHolder> {

    private List<Balancelist> balanceList;
    private Context context;
    /**
     * View holder class
     */


    public BalanceAdapter(Context context, List<Balancelist> balanceList) {
        this.context = context;
        this.balanceList = balanceList;
    }
    @Override
    public void onBindViewHolder(BalanceAdapter.MyViewHolder holder, int position) {

        Balancelist c = balanceList.get(position);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
        try {
            Date date = df.parse(c.la_createdt);
            df.applyPattern("yyyy/MM/dd hh:mm:ss aa");
            String result = df.format(date);

            holder.balance_date_time.setText(""+result);

        } catch (ParseException e) {
            e.printStackTrace();
        }




        holder.balance_type.setText(c.la_type);
        holder.balance_reference.setText(c.la_ref);

        if(c.la_amount.contains("-"))
        {
            holder.balance_amount.setTextColor(Color.parseColor("#FF0000"));
            String text=c.la_amount.replace("-","");
            holder.balance_amount.setText(text);
        }
        else
        {
            holder.balance_amount.setTextColor(Color.parseColor("#1F45FC"));
            holder.balance_amount.setText(c.la_amount);
        }

    }

    @Override
    public int getItemCount() {

        return balanceList.size();
    }

    @Override
    public BalanceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_list_row, parent, false);

        BalanceAdapter.MyViewHolder viewHolder = new BalanceAdapter.MyViewHolder(v);
        return viewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView balance_date_time;
        public TextView balance_type;
        public TextView balance_reference;
        public TextView balance_amount;

        public MyViewHolder(View view) {
            super(view);


            balance_date_time = (TextView) view.findViewById(R.id.date);
            balance_type = (TextView) view.findViewById(R.id.type);
            balance_reference = (TextView) view.findViewById(R.id.reference);
            balance_amount = (TextView) view.findViewById(R.id.amount);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, String.valueOf(getLayoutPosition()),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
