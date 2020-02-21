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
import ths.ScanPay_User.GetFunction.GetUserProfileListTask;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.SignUpStep1;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostSignUp_Validate_LoginID_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String loginid;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostSignUp_Validate_LoginID_Task(Context context){
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
        loginid=param1;
        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostSignUp_Validate_LoginID_Api;
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

            if(result.equals("This Login ID not available"))
            {
                SignUpStep1.checkloginidresult.setVisibility(View.VISIBLE);
                SignUpStep1.checkloginidresult.setText("This Login ID is not available");

            }
            else if(result.equals("This Login allow"))
            {
                SignUpStep1.checkloginidresult.setVisibility(View.INVISIBLE);
                                SignUpStep1.verifybool=true;

                SignUpStep1.otplayout.setVisibility(View.VISIBLE);
                 SignUpStep1.verifybtn.setVisibility(View.GONE);
                SignUpStep1.countresend();
                SignUpStep1.sendOTP();
                new PostSignUp_Send_OTP_Task(context).execute(loginid);

            }




        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
