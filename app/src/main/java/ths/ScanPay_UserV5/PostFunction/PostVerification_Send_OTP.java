package ths.ScanPay_UserV5.PostFunction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.FindMerchantlist;
import ths.ScanPay_UserV5.NetworkUtil;
import ths.ScanPay_UserV5.Verification_LoginPage;

public class PostVerification_Send_OTP extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String encryptedString;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostVerification_Send_OTP(Activity context){
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


        try {
            encryptedString = Encrypt("s7OyGTP6ZZmL7t3z", "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostVerification_Send_OTP_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("LoginID", param1);
            hashMap.put("Token",encryptedString);
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
    protected void onPostExecute(final String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();


           Verification_LoginPage.countresend();
           Verification_LoginPage.systemOTP = result;
           Verification_LoginPage.otpedit.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                   if(result.equals(Verification_LoginPage.otpedit.getText().toString()))
                   {
                       Verification_LoginPage.loginbtn.setVisibility(View.VISIBLE);
                       Verification_LoginPage.loginbtn.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               new PostVerification_Save_OTP(context).execute("60"+Verification_LoginPage.loginidedit.getText().toString(),result);
                           }
                       });

                   }
                   else
                   {
                       Verification_LoginPage.loginbtn.setVisibility(View.INVISIBLE);
                   }
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });




    }


    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Error #B0090 Internet Connection Failed")
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
