package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;
import ths.ScanPay_User.TopUpScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostTopup_Send_OTP_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostTopup_Send_OTP_Task(Context context){
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
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Send_OTP_Api ;
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
    protected void onPostExecute(final String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        TopUpScanQRActivity.set_new_Otp_layout.setVisibility(View.VISIBLE);
        TopUpScanQRActivity.user_number.setText(MainActivity.LoginID);
        TopUpScanQRActivity.resendotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostTopup_Send_OTP_Task(context).execute(MainActivity.LoginID);
            }
        });
        TopUpScanQRActivity.new_otp_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(result.equals(TopUpScanQRActivity.new_otp_edit.getText().toString()))
                {
                    TopUpScanQRActivity.saveotpbtn.setVisibility(View.VISIBLE);
                    TopUpScanQRActivity.saveotpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new PostTopup_Save_OTP_Task(context).execute(MainActivity.LoginID,result);
                        }
                    });

                }
                else
                {
                    TopUpScanQRActivity.saveotpbtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });









        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
