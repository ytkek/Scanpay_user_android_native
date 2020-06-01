package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_Confirm_Pay_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;


    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_Confirm_Pay_Task(Activity context){
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
        String params5 = params[4];
        String params6 = params[5];
        String params7 = params[6];

        if (params6 != null && !params6.isEmpty())
        {

        }
        else
        {
            params6 = "";
        }
        if (params7 != null && !params7.isEmpty())
        {

        }
        else
        {
            params7 = "";
        }


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Confirm_Pay_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID" , params1);
            hashMap.put("MerchantID",params2);
            hashMap.put("MerchantName",params3);
            hashMap.put("type",params4);
            hashMap.put("Amount",params5);
            hashMap.put("qrcode",params6);
            hashMap.put("dyqrcode",params7);






            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{

            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }





        }
        else
        {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            });
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        if(result.equals("payment success"))
        {
            PaymentScanQRActivity.payment_layout.setVisibility(View.GONE);


            PaymentScanQRActivity.payment_result_amount.setText("Amount: "+PaymentScanQRActivity.amount_edit.getText().toString());
            PaymentScanQRActivity.payment_result_date.setText("Date: "+java.text.DateFormat.getDateTimeInstance().format(new Date()));
            PaymentScanQRActivity.payment_result_merchant.setText(PaymentScanQRActivity.merchantname);
            PaymentScanQRActivity.payment_result_layout.setVisibility(View.VISIBLE);
        }
        else
        {

        }









        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }
    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Connect to Internet or quit")
                .setCancelable(false)
                .setPositiveButton("Connect to Internet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        context.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }





}
