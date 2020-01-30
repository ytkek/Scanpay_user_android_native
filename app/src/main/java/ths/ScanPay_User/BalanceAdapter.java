package ths.ScanPay_User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
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


        holder.balance_date_time.setText(c.getBalance_date());
        holder.balance_type.setText(c.getBalance_type());
        holder.balance_reference.setText(c.getBalance_reference());
        holder.balance_amount.setText(c.getBalance_amount());
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
