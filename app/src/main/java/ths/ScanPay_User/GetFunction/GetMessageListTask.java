package ths.ScanPay_User.GetFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantActivity;
import ths.ScanPay_User.FindMerchantAdapter;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MessageCentreActivity;
import ths.ScanPay_User.MessageCentreAdapter;
import ths.ScanPay_User.MessageCentrelist;
import ths.ScanPay_User.MessageNotification.MessageNotification;
import ths.ScanPay_User.NetworkUtil;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetMessageListTask extends AsyncTask<Void, Integer, ArrayList<MessageCentrelist>> {

    public Context context = null;
    public static ArrayList<MessageCentrelist> listMockData;
    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public GetMessageListTask(Context context, RecyclerView list)
    {
        this.context = context;
        this.list=list;


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
    protected ArrayList<MessageCentrelist> doInBackground(Void... params)
    {
        String apiUrl = ApiUrl.Domain + ApiUrl.FindMessageListApi;
        listMockData = new ArrayList<MessageCentrelist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            String response = NetworkUtil.SentGet(apiUrl,false);
            try
            {
                JSONObject jObj = new JSONObject(response);
                JSONArray jArray = jObj.getJSONArray("datarecords");
                for (int i = 0; i < jArray.length(); i++)
                {
                    JSONObject tmp = jArray.getJSONObject(i);


                    MessageCentrelist newsData = new MessageCentrelist();

                    if (tmp.has("nob_id"))
                    {
                        newsData.setId(tmp.getString("nob_id"));
                       // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_title"))
                    {
                        newsData.setTitle(tmp.getString("nob_title"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_message"))
                    {
                        newsData.setMessage(tmp.getString("nob_message"));
                        // Log.d("wtf",newsData.getM_id());
                    }
                    if (tmp.has("nob_publishdate"))
                    {
                        newsData.setDate(tmp.getString("nob_publishdate"));
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
    protected void onPostExecute(final ArrayList<MessageCentrelist> result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

       // Collections.sort(result, Collections.reverseOrder());
        Collections.sort(result, new Comparator<MessageCentrelist>() {
            @Override
            public int compare(MessageCentrelist lhs, MessageCentrelist rhs) {
                return rhs.getId().compareTo(lhs.getId());
            }
        });


        List<Integer> num = new ArrayList<>();
        Log.d("wtf2",""+result.size() + "" + MessageCentreActivity.db.getAllMessage().size());
        if(result.size()==MessageCentreActivity.db.getAllMessage().size())
        {
             //refreshdatabase(result);
            MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

            MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);

        }
        else if (MessageCentreActivity.db.getAllMessage().size()==0)
        {
            for (int j=0;j<result.size();j++)
            {
                MessageCentreActivity.db.insertIndicator("false",result.get(j).getId());

                if(j+1==result.size())
                {
                    MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

                    MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);
               }
            }

        }
        else if(result.size()>MessageCentreActivity.db.getAllMessage().size()&&MessageCentreActivity.db.getAllMessage().size()!=0)
        {

            for (int a = 0; a<result.size(); a++)
           {
                for(int b=0; b<MessageCentreActivity.db.getAllMessage().size(); b++)
                {
                    if(result.get(a).getId().equals(MessageCentreActivity.db.getAllMessage().get(b).getNob_id()))
                    {
                        num.add(a);
                    }
                    else
                    {

                    }

                }

                if(a+1==result.size())
                {


                   // MessageCentreActivity.arraylist_messagedb.clear();
                   // MessageCentreActivity.arraylist_messagedb.addAll(MessageCentreActivity.db.getAllMessage());
                    for (int i = 0; i < result.size(); i++){


                        if (!num.contains(i)) {
                            MessageCentreActivity.db.insertIndicator("false", result.get(i).getId());
                        }


                               if(i+1==MessageCentreActivity.db.getAllMessage().size())
                                {
                                    MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

                                    MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);
                                }
                            }

                        }
                    }
       }









    }



    public void refreshdatabase(ArrayList<MessageCentrelist> result)
    {


        for (int i =0; i<result.size(); i ++)
        {
            MessageCentreActivity.arraylist_messagedb.addAll(MessageCentreActivity.db.getAllMessage());
           // MessageCentreActivity.db.insertIndicator("false",result.get(i).getId());
            Log.d("wtf2",""+MessageCentreActivity.arraylist_messagedb.get(i).getIndicator());
            if(i+1==result.size())
            {
                MessageCentreActivity.db.deleteall();
                MessageCentreActivity.mAdapter = new MessageCentreAdapter(context,result);

                MessageCentreActivity.recyclerView.setAdapter(MessageCentreActivity.mAdapter);
            }
        }

    }



}
