package ths.ScanPay_UserV5.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.FindMerchantlist;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;
import ths.ScanPay_UserV5.PaymentScanQRActivity;
import ths.ScanPay_UserV5.Payment_Confirm_Dialog;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_Validate_PinNumber_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String params1,params2;
    String encryptedString;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_Validate_PinNumber_Task(Activity context){
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
        String params3= params[2];
        String params4 = params[3];

        try {
            encryptedString = Encrypt(params3 + "+" + params4, "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Validate_PinNumber_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", params1);
            hashMap.put("Pin_Number",params2);
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
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        if(result.equals("Valid Pin Number"))
        {

            double a ;
            double b;
            double c;
            double d;
            a = Double.parseDouble(PaymentScanQRActivity.credit_balance);
            b= Double.parseDouble(PaymentScanQRActivity.qr_amount);
            c = Double.parseDouble(PaymentScanQRActivity.dailyexp);
            d = Double.parseDouble(PaymentScanQRActivity.monthlyexp);
            if (b>a)

            {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error #B0035 Not Enough Credit")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0035 Not Enough Credit");
                                PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            }
            else if (b > c)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error #B0036 Not allow to exceed daily limit")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0036 Not allow to exceed daily limit");
                                PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
            else if (b>(200.00))
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error #B0037 Not allow to exceed purse limit")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0037 Not allow to exceed purse limit");
                                PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
            else if (b > d)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error #B0038 Not allow to exceed monthly limit")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0038 Not allow to exceed monthly limit");
                                PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
            else

            {

                Payment_Confirm_Dialog cdd=new Payment_Confirm_Dialog(context,PaymentScanQRActivity.merchantname,PaymentScanQRActivity.amount_edit.getText().toString());

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);



            }
        }
        else if(result.equals("Invalid Pin Number"))
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Error #B0031 Invalid Pin Number")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0031 Invalid Pin Number");
                            PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
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
