package ths.ScanPay_User.GetFunction;

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

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.EditProfileActivity;
import ths.ScanPay_User.EditProfilelist;
import ths.ScanPay_User.GlobalVariable;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.SettingFragment;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetUserProfile_Name_ID_Task extends AsyncTask<Void, Integer, ArrayList<EditProfilelist>> {

    public Activity context = null;
    public static ArrayList<EditProfilelist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public GetUserProfile_Name_ID_Task(Activity context)
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
    protected ArrayList<EditProfilelist> doInBackground(Void... params)
    {



        String apiUrl = ApiUrl.Domain + ApiUrl.GetUserProfileListApi;
        listMockData = new ArrayList<EditProfilelist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

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
                       // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_login"))
                    {
                        newsData.setMl_login(tmp.getString("ml_login"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_Name"))
                    {
                        newsData.setMl_name(tmp.getString("ml_Name"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_valid"))
                    {
                        newsData.setMl_valid(tmp.getString("ml_valid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_password"))
                    {
                        newsData.setMl_password(tmp.getString("ml_password"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_updatedate"))
                    {
                        newsData.setMl_updatedate(tmp.getString("ml_updatedate"));
                    // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_updateby"))
                    {
                        newsData.setMl_updateby(tmp.getString("ml_updateby"));
                        // Log.d("wtf",newsData.getM_id());
                    }

                    if (tmp.has("ml_hpno"))
                    {
                        newsData.setMl_hpno(tmp.getString("ml_hpno"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_hpcc"))
                    {
                        newsData.setMl_hpcc(tmp.getString("ml_hpcc"));
                        // Log.d("wtf",newsData.getM_id());
                    }

                    if (tmp.has("ml_gender"))
                    {
                        newsData.setMl_gender(tmp.getString("ml_gender"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_email"))
                    {
                        newsData.setMl_email(tmp.getString("ml_email"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_remarks"))
                    {
                        newsData.setMl_remarks(tmp.getString("ml_remarks"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("ml_nickname"))
                    {
                        newsData.setMl_nickname(tmp.getString("ml_nickname"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if(tmp.has("ml_dob"))
                    {
                        newsData.setMl_dob(tmp.getString("ml_dob"));
                    }
                    if (tmp.has("ml_paymentpin"))
                    {
                        newsData.setMl_paymentpin(tmp.getString("ml_paymentpin"));
                        // Log.d("wtf",newsData.getM_id());
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

        }
        else
        {
            SettingFragment.ID.setText(result.get(0).getMl_login());
            SettingFragment.username.setText(result.get(0).getMl_name());
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
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}