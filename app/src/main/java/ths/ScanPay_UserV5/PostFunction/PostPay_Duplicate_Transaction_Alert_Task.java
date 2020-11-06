package ths.ScanPay_UserV5.PostFunction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;
import ths.ScanPay_UserV5.PaymentScanQRActivity;
import ths.ScanPay_UserV5.Verification_LoginPage;

public class PostPay_Duplicate_Transaction_Alert_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;


    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;
    String encryptedString;


    public PostPay_Duplicate_Transaction_Alert_Task(Activity context){
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
        String param2 = params[1];
        String param3 = params[2];
        String param4 = params[3];
        String param5 = params[4];
        String param6 = params[5];



        try {
            encryptedString = Encrypt(param5 + "+" + param6, "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Duplicate_Transaction_Alert_Api ;

        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();


            hashMap.put("Token",encryptedString);
            hashMap.put("Type", param1);
            hashMap.put("Amount", param2);
            hashMap.put("MemberID", param3);
            hashMap.put("MerchantID", param4);
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

        //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        if (result.equals("Transaction Duplicate Not Found"))
       {
      //      new PostVerification_Validate_Email(context).execute(Verification_LoginPage.emailedit.getText().toString());
      //      Verification_LoginPage.checkloginresult.setVisibility(View.GONE);
           new PostPay_Validate_PinNumber_Task(PaymentScanQRActivity.PaymentScanQRActivityactivity).execute(MainActivity.LoginID,PaymentScanQRActivity.pin1.getText().toString()+PaymentScanQRActivity.pin2.getText().toString()+PaymentScanQRActivity.pin3.getText().toString()+PaymentScanQRActivity.pin4.getText().toString()+PaymentScanQRActivity.pin5.getText().toString()+PaymentScanQRActivity.pin6.getText().toString(),MainActivity.LoginID,MainActivity.Password);
        }
        else if (result.equals("Transaction Duplicate Found"))
       {


           AlertDialog.Builder builder = new AlertDialog.Builder(context);
           builder.setMessage("Same merchant and amount detected, Are you sure to continue?")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            new PostPay_Validate_PinNumber_Task(PaymentScanQRActivity.PaymentScanQRActivityactivity).execute(MainActivity.LoginID,PaymentScanQRActivity.pin1.getText().toString()+PaymentScanQRActivity.pin2.getText().toString()+PaymentScanQRActivity.pin3.getText().toString()+PaymentScanQRActivity.pin4.getText().toString()+PaymentScanQRActivity.pin5.getText().toString()+PaymentScanQRActivity.pin6.getText().toString(),MainActivity.LoginID,MainActivity.Password);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                              dialog.dismiss();

                        context.finish();
                        PaymentScanQRActivity.clear();
                         }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }

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
