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
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_MerchantInfo_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;


    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_MerchantInfo_Task(Context context){
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
        String params1= params[0];
        String params2 = params[1];
        String params3= params[2];
        String params4 = params[3];



        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_MerchantInfo_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("type" , params1);
            hashMap.put("merchantid",params2);
            if(params1=="pay")
            {
                hashMap.put("amount",params3);
                hashMap.put("dynamicqrcode",params4);
            }
            else if (params1=="pay_cashier") {
                hashMap.put("qrcode", params3);
            }


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


            if(result.equals("Invalid Merchant"))
            {

                PaymentScanQRActivity.payment_layout.setVisibility(View.GONE);
                PaymentScanQRActivity.error_message.setText("INVALID MERCHANT!!!!");
                PaymentScanQRActivity.error_message.setVisibility(View.VISIBLE);
            }
            else
            {
                PaymentScanQRActivity.merchant_name.setText("Transaction with merchant "+result);
            }










        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
