package ths.ScanPay_UserV5.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import ths.ScanPay_UserV5.Generic.GenericAutologin;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    String CHANNEL_ID="ScanPay_User";
    int  NOTIFICATION_ID = 100;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("wtf", "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d("wtf", "Message data payload: " + remoteMessage.getData().get("body")+","+ remoteMessage.getData().get("title"));

            showNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"));
        }

        if (remoteMessage.getNotification() != null) {
            Log.d("wtf", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d("wtf", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        //showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }
    private void showNotification(String title, String body)
    {
        createNotificationChannel();
        Intent resultintent = new Intent(this, MainActivity.class);
        resultintent.putExtra("LoginID", GenericAutologin.getLogin(this));
        resultintent.putExtra("Page", "MessagePage");
        PendingIntent resultpendingintent = PendingIntent.getActivity(this,1,resultintent,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)


                .setSmallIcon(R.drawable.myscanpay)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(resultpendingintent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(new Random().nextInt(), mBuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                //using same string for both channelid and name. ideally use different strings
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
