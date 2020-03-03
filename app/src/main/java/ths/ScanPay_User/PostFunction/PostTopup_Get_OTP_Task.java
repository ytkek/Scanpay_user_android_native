package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.Generic.Generic;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;
import ths.ScanPay_User.TopUpScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostTopup_Get_OTP_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostTopup_Get_OTP_Task(Context context){
        this.context = context;



    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progDailog = new ProgressDialog(context);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();

    }


    @Override
    protected String doInBackground(String... params)
    {
        String param1= params[0];

        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Validate_OTP_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", param1);
            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{

            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }





        }

        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();


        String otp= Generic.getOtp(context);
        if(otp.equals(""))
        {
            TopUpScanQRActivity.OTPlayout.setVisibility(View.VISIBLE);
            TopUpScanQRActivity.otp_empty.setVisibility(View.VISIBLE);
            TopUpScanQRActivity.getnewotpbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TopUpScanQRActivity.OTPlayout.setVisibility(View.GONE);
                    new PostTopup_Send_OTP_Task(context).execute(MainActivity.LoginID);
                }
            });
        }
        else if (!result.equals(otp))
        {
            TopUpScanQRActivity.OTPlayout.setVisibility(View.VISIBLE);
            TopUpScanQRActivity.otp_different.setVisibility(View.VISIBLE);
            TopUpScanQRActivity.getnewotpbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TopUpScanQRActivity.OTPlayout.setVisibility(View.GONE);
                   new PostTopup_Send_OTP_Task(context).execute(MainActivity.LoginID);
                }
            });
        }
        else if (result.equals(otp))
        {
            Toast.makeText(context,"correct OTP",Toast.LENGTH_SHORT).show();
            TopUpScanQRActivity.topup_layout.setVisibility(View.VISIBLE);
        }




        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
