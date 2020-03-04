package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.BalanceActivity;
import ths.ScanPay_User.BalanceAdapter;
import ths.ScanPay_User.Balancelist;
import ths.ScanPay_User.EditProfilelist;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostBalance_CurrentDate_Statement_Task extends AsyncTask<Void, Integer, ArrayList<Balancelist>> {

    public Context context = null;
    public static ArrayList<Balancelist> listMockData;
    RecyclerView list;
    String params1,params2;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostBalance_CurrentDate_Statement_Task(Context context){
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
    protected ArrayList<Balancelist> doInBackground(Void... params)
    {




        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostBalance_CurrentDate_Statement_Api ;
        listMockData = new ArrayList<Balancelist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", MainActivity.LoginID);

            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("datarecords");
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject tmp = jArray.getJSONObject(i);
                    Balancelist newsData = new Balancelist();
                    if (tmp.has("la_id"))
                    {
                        newsData.setLa_id(tmp.getString("la_id"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_type"))
                    {
                        newsData.setLa_type(tmp.getString("la_type"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_ref"))
                    {
                        newsData.setLa_ref(tmp.getString("la_ref"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_createdt"))
                    {
                        newsData.setLa_createdt(tmp.getString("la_createdt"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_createby"))
                    {
                        newsData.setLa_createby(tmp.getString("la_createby"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_amount"))
                    {
                        newsData.setLa_amount(tmp.getString("la_amount"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_merchantid"))
                    {
                        newsData.setLa_merchantid(tmp.getString("la_merchantid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_memberid"))
                    {
                        newsData.setLa_memberid(tmp.getString("la_memberid"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_points"))
                    {
                        newsData.setLa_points(tmp.getString("la_points"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_ref2"))
                    {
                        newsData.setLa_ref2(tmp.getString("la_ref2"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("la_revid"))
                    {
                        newsData.setLa_revid(tmp.getString("la_revid"));
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

        return listMockData;
    }

    @Override
    protected void onPostExecute(ArrayList<Balancelist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        BalanceActivity.mAdapter = new BalanceAdapter(context,result);

        BalanceActivity.recyclerView.setAdapter(BalanceActivity.mAdapter);



    }





}
