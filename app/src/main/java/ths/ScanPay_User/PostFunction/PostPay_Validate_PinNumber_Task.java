package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.EditProfileDialog;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;
import ths.ScanPay_User.Payment_Confirm_Dialog;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_Validate_PinNumber_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String params1,params2;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_Validate_PinNumber_Task(Context context){
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
        params1= params[0];
        params2=params[1];


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Validate_PinNumber_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", params1);
            hashMap.put("Pin_Number",params2);

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

        if(result.equals("Valid Pin Number"))
        {
            Payment_Confirm_Dialog cdd=new Payment_Confirm_Dialog(context,PaymentScanQRActivity.merchantname,PaymentScanQRActivity.amount_edit.getText().toString());

            cdd.show();
            Window window = cdd.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           // new PostPay_Confirm_Pay_Task(context).execute(MainActivity.LoginID,PaymentScanQRActivity.merchantid,PaymentScanQRActivity.merchantname,PaymentScanQRActivity.type,PaymentScanQRActivity.amount_edit.getText().toString(),PaymentScanQRActivity.qrcode,PaymentScanQRActivity.lqrcode);
        }
        else if(result.equals("Invalid Pin Number"))
        {
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        }












        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
