package ths.ScanPay_UserV5.GetFunction;

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

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.EditProfileActivity;
import ths.ScanPay_UserV5.EditProfilelist;
import ths.ScanPay_UserV5.GlobalVariable;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetUserProfileListTask extends AsyncTask<String, Integer, ArrayList<EditProfilelist>> {

    public Activity context = null;
    public static ArrayList<EditProfilelist> listMockData;
    RecyclerView list;
    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;
    String encryptedString;

    public GetUserProfileListTask(Activity context)
    {
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
    protected ArrayList<EditProfilelist> doInBackground(String... params)
    {
        String params1= params[0];
        String params2 = params[1];

        try {
            encryptedString = Encrypt(params1 + "+" + params2, "@McQfTjWnZq4t7w!");

            Log.e("AES",encryptedString);

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        String apiUrl = ApiUrl.Domain + ApiUrl.GetUserProfileListApi;
        listMockData = new ArrayList<EditProfilelist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("Token" , encryptedString);
            hashMap.put("LoginID", MainActivity.LoginID);
            String response = NetworkUtil.sendPost(apiUrl,hashMap);

            try
            {
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("datarecords");
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject tmp = jArray.getJSONObject(i);

                    EditProfilelist newsData = new EditProfilelist();

                    if (tmp.has("ml_id"))
                    {
                        newsData.setMl_id(tmp.getString("ml_id"));

                    }
                    if (tmp.has("ml_login"))
                    {
                        newsData.setMl_login(tmp.getString("ml_login"));

                    }
                    if (tmp.has("ml_Name"))
                    {
                        newsData.setMl_name(tmp.getString("ml_Name"));

                    }
                    if (tmp.has("ml_valid"))
                    {
                        newsData.setMl_valid(tmp.getString("ml_valid"));

                    }
                    if (tmp.has("ml_password"))
                    {
                        newsData.setMl_password(tmp.getString("ml_password"));

                    }
                    if (tmp.has("ml_updatedate"))
                    {
                        newsData.setMl_updatedate(tmp.getString("ml_updatedate"));

                    }
                    if (tmp.has("ml_updateby"))
                    {
                        newsData.setMl_updateby(tmp.getString("ml_updateby"));

                    }

                    if (tmp.has("ml_hpno"))
                    {
                        newsData.setMl_hpno(tmp.getString("ml_hpno"));

                    }
                    if (tmp.has("ml_hpcc"))
                    {
                        newsData.setMl_hpcc(tmp.getString("ml_hpcc"));

                    }

                    if (tmp.has("ml_gender"))
                    {
                        newsData.setMl_gender(tmp.getString("ml_gender"));

                    }
                    if (tmp.has("ml_email"))
                    {
                        newsData.setMl_email(tmp.getString("ml_email"));

                    }
                    if (tmp.has("ml_remarks"))
                    {
                        newsData.setMl_remarks(tmp.getString("ml_remarks"));

                    }
                    if (tmp.has("ml_nickname"))
                    {
                        newsData.setMl_nickname(tmp.getString("ml_nickname"));

                    }
                    if(tmp.has("ml_dob"))
                    {
                        newsData.setMl_dob(tmp.getString("ml_dob"));
                    }
                    if (tmp.has("ml_paymentpin"))
                    {
                        newsData.setMl_paymentpin(tmp.getString("ml_paymentpin"));
                    }





                    listMockData.add(newsData);
                }

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

        return listMockData;
    }

    @Override
    protected void onPostExecute(final ArrayList<EditProfilelist> result) {
        super.onPostExecute(result);

        progDailog.dismiss();

        if(result.isEmpty())
        {

        }else {



            EditProfileActivity.changefullname.setText(result.get(0).getMl_name() + " ");
            GlobalVariable.OldName = result.get(0).getMl_name();


            EditProfileActivity.changeid.setText(result.get(0).getMl_login() + " ");
            EditProfileActivity.changepassword.setText(result.get(0).getMl_password() + " ");

            EditProfileActivity.changeemail.setText(result.get(0).getMl_email() + " ");
            GlobalVariable.OldEmail = result.get(0).getMl_email();

            EditProfileActivity.changemobile.setText(result.get(0).getMl_hpno() + " ");
            GlobalVariable.OldMobileNumber = result.get(0).getMl_hpno();

            if (result.get(0).getMl_gender().equals("M")) {
                EditProfileActivity.changegender.setText("male");
                GlobalVariable.OldGender = "male";
            } else if (result.get(0).getMl_gender().equals("F")) {
                EditProfileActivity.changegender.setText("female");
                GlobalVariable.OldGender = "female";
            }

            EditProfileActivity.changenickname.setText(result.get(0).getMl_nickname() + " ");
            GlobalVariable.OldNickName = result.get(0).getMl_nickname();

            EditProfileActivity.changeremark.setText(result.get(0).getMl_remarks() + " ");
            GlobalVariable.OldRemarks = result.get(0).getMl_remarks();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                Date date = format.parse(result.get(0).getMl_dob());
                EditProfileActivity.changedob.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(date) + " ");
                GlobalVariable.OldDob = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            EditProfileActivity.changepin.setText(result.get(0).getMl_paymentpin() + " ");
            GlobalVariable.OldPin = result.get(0).getMl_paymentpin();


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
