package com.jesulonimi.user.fingerprintauth;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        private ImageView fingerPrintImage;
        private TextView   headingText;
        private TextView   descText;
            FingerprintManager fingerprintManager;
            KeyguardManager keyguardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
keyguardManager=(KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            fingerPrintImage=findViewById(R.id.fingerprintimage);
            headingText=findViewById(R.id.headinglabel);
            descText=findViewById(R.id.descText);
        //TODO:CHECK 1 ANDRID DVICE SHOULD BE GREATER THAN OR EQUAL TO MARSHMALLOW
        //TODO:CHECK IF DEVICE PRINT SCANNER EXIST
        //TODO: HAVE PERMISSION TO USE FINGERPRINT SCANER
        //TODO:LOCK SCREEN IS SECURED WITH AT LEAST ONE KIND OF LOCK
        //TODO:AT LEAST ONE FINGER PRINT IS REGISTERRED FOR THE DEVICE

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                fingerprintManager=(FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
                if(!fingerprintManager.isHardwareDetected()){
                    descText.setText("no finger print detected");
                }else if(ContextCompat.checkSelfPermission(this,Manifest.permission.USE_FINGERPRINT)!=PackageManager.PERMISSION_GRANTED){

                    descText.setText("no permisssions given");
                }else if(!keyguardManager.isKeyguardSecure()){
                    descText.setText("add lock to your device");
                }else if(!fingerprintManager.hasEnrolledFingerprints()){
                    descText.setText("add at least one finger print to your phone");
                }else{
                    descText.setText("place your finger on the scanner");
                    FingerPrintHandler fingerPrintHandler=new FingerPrintHandler(this);
                    fingerPrintHandler.startAuth(fingerprintManager,null);
                }
        }
    }
}
