package ths.ScanPay_User;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureActivity;

public class CaptureActivityPortrait extends CaptureActivity {
  public static Boolean quitQR_indicator = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finish();
       quitQR_indicator=true;
        Log.d("wtf","WTFWTF");


    }
}