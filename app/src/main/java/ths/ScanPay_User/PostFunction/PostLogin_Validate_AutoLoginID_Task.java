package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.Generic.GenericAutologin;
import ths.ScanPay_User.Login;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostLogin_Validate_AutoLoginID_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String params1,params2;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostLogin_Validate_AutoLoginID_Task(Context context){
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
        params2= params[1];


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostLogin_Validate_LoginID_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", params1);
            hashMap.put("Password", params2);

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


        if(result.equals("Allow Login"))
        {
            GenericAutologin.SaveLoginPassword(params1,params2,context);


            Intent mainactivity = new Intent(context, MainActivity.class);
            mainactivity.putExtra("LoginID",params1);
             context.startActivity(mainactivity);
        }
        else if(result.equals("Not Allow Login"))
        {
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
            Intent login = new Intent(context, Login.class);
            context.startActivity(login);
        }
        else
        {
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
            Intent login = new Intent(context, Login.class);
            context.startActivity(login);


        }






        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

    }





}
