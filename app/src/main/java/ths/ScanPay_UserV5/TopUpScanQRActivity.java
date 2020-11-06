package ths.ScanPay_UserV5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.concurrent.TimeUnit;

import ths.ScanPay_UserV5.PostFunction.PostApp_Error_Message_Task;
import ths.ScanPay_UserV5.PostFunction.PostTopUp_MerchantInfo_Task;
import ths.ScanPay_UserV5.PostFunction.PostTopup_CreditBalance_Task;
import ths.ScanPay_UserV5.PostFunction.PostTopup_Get_OTP_Task;

public class TopUpScanQRActivity extends AppCompatActivity {

    public final int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;
    public static String type,merchantid,amount,lqrcode,qrcode,merchantname,qr_amount;
    public static EditText amount_edit,new_otp_edit;
    public static TextView checkdailylimit,merchant_name,credit_balance;

    public static LinearLayout topup_layout,OTPlayout,set_new_Otp_layout,topup_result_layout;
    public static TextView otp_empty,otp_different;
    public static TextView topup_result_amount,topup_result_date,topup_result_merchant;

    public static Button getnewotpbtn,resendotpbtn,saveotpbtn,topup_close_btn;

    public static TextView error_message,user_number;

    ImageView back_btn;

    Button confirm_btn;
    Activity TopUpScanQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TopUpScanQR = this;
        setContentView(R.layout.topupscanqr_activity);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a QRcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();


        back_btn=(ImageView) findViewById(R.id.backbtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                clear();

            }
        });

        OTPlayout=(LinearLayout)findViewById(R.id.OTPlayout);
        OTPlayout.setVisibility(View.GONE);

        otp_empty=(TextView)findViewById(R.id.otp_empty_textview);
        otp_empty.setVisibility(View.GONE);

        otp_different=(TextView)findViewById(R.id.otp_different_textview);
        otp_different.setVisibility(View.GONE);

        getnewotpbtn=(Button)findViewById(R.id.getnewotpbtn);



        set_new_Otp_layout=(LinearLayout)findViewById(R.id.set_new_otp_layout);
        set_new_Otp_layout.setVisibility(View.GONE);

        resendotpbtn=(Button)findViewById(R.id.resendotpbtn);

        user_number=(TextView)findViewById(R.id.user_number);

        new_otp_edit=(EditText)findViewById(R.id.new_otp_edit);

        saveotpbtn = (Button)findViewById(R.id.saveotpbtn);
        saveotpbtn.setVisibility(View.INVISIBLE);

        topup_layout=(LinearLayout)findViewById(R.id.topup_layout);
        topup_layout.setVisibility(View.GONE);

        merchant_name = (TextView)findViewById(R.id.merchant_name);
        amount_edit=(EditText)findViewById(R.id.amount_edit);

        credit_balance=(TextView)findViewById(R.id.credit_balance);

        error_message=(TextView)findViewById(R.id.error_message);
        error_message.setVisibility(View.INVISIBLE);
        confirm_btn= (Button)findViewById(R.id.confirmbtn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUp_Confirm_Dialog cdd=new TopUp_Confirm_Dialog(TopUpScanQR,TopUpScanQRActivity.merchantname,TopUpScanQRActivity.amount_edit.getText().toString());

                cdd.show();
                Window window = cdd.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

        topup_result_layout=(LinearLayout)findViewById(R.id.topup_result_layout);
        topup_result_layout.setVisibility(View.GONE);

        topup_result_amount = (TextView)findViewById(R.id.amount_success);
        topup_result_date = (TextView)findViewById(R.id.date_success);
        topup_result_merchant = (TextView)findViewById(R.id.merchant_success);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                Toast.makeText(this, "REQUEST_CODE = " + requestCode, Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if(result.getContents() == null) {

        } else {

            try {

                String[] arrayString = result.getContents().split("\\|\\|");
                if (arrayString.length <= 2 || arrayString.length > 3) {
                    OTPlayout.setVisibility(View.GONE);
                    set_new_Otp_layout.setVisibility(View.GONE);
                    otp_empty.setVisibility(View.GONE);
                    otp_different.setVisibility(View.GONE);
                    TopUpScanQRActivity.topup_layout.setVisibility(View.GONE);
                    TopUpScanQRActivity.error_message.setText("INVALID MERCHANT");
                    TopUpScanQRActivity.error_message.setVisibility(View.VISIBLE);

                    AlertDialog.Builder builder = new AlertDialog.Builder(TopUpScanQR);
                    builder.setMessage("Error #B0041 Invalid Merchant")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    new PostApp_Error_Message_Task(TopUpScanQR).execute(MainActivity.LoginID,"unsuccessful topup Error #B0041 Invalid Merchant");
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();


                } else {
                    new PostTopup_Get_OTP_Task(this).execute(MainActivity.LoginID,MainActivity.LoginID,MainActivity.Password);

                    new PostTopup_CreditBalance_Task(this).execute(MainActivity.LoginID,MainActivity.LoginID,MainActivity.Password);

                    for (int a = 0; a < arrayString.length; a++) {
                        if (arrayString.length == 3) {
                            type = "topup";
                            merchantid = arrayString[0];
                            amount = arrayString[1];
                            qr_amount = amount;
                            lqrcode = arrayString[2];
                            new PostTopUp_MerchantInfo_Task(this).execute(type, merchantid, amount, lqrcode, qrcode,MainActivity.LoginID,MainActivity.Password);
                            Log.d("Scanned: ", "pay" + arrayString[a]);
                        }

                    }
                }
            }
            catch (Exception ex)
            {

            }

        }
    }
    public static void clear()
    {

        type="";
        merchantid="";
        amount="";
        lqrcode="";
        qrcode="";
        merchantname="";
        merchant_name.setText("");
        amount_edit.setText("");
        credit_balance.setText("");
        qr_amount="";
    }


    @Override
    public void onResume()
    {
        super.onResume();

        if(CaptureActivityPortrait.quitQR_indicator==true)
        {
            finish();
            CaptureActivityPortrait.quitQR_indicator=false;
        }
    }
    public static void countresend()
    {
        new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                resendotpbtn.setText( String.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) );
                resendotpbtn.setEnabled(false);
            }

            public void onFinish() {
                resendotpbtn.setText("RESEND");
                resendotpbtn.setEnabled(true);
            }
        }.start();


    }
}
