package ths.ScanPay_UserV5.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.FindMerchantlist;
import ths.ScanPay_UserV5.Generic.GenericAutologin;
import ths.ScanPay_UserV5.Login;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostLogin_Validate_AutoLoginID_Task extends AsyncTask<String, Integer, String> {

    public Context context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String params1,params2;
    String encryptedString;

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
        try {
            encryptedString = Encrypt("s7OyGTP6ZZmL7t3z", "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostLogin_Validate_LoginID_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", params1);
            hashMap.put("Password", params2);
            hashMap.put("Token",encryptedString);

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
    public static String Encrypt(String text,String key)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;

        if (len > keyBytes.length)
            len = keyBytes.length;

        System.arraycopy(b,0,keyBytes,0,len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes,"AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));




        return Base64.encodeToString(results,Base64.DEFAULT);
    }






}
