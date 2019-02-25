package com.jesulonimi.user.fingerprintauth;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {
    Context context;
        public FingerPrintHandler(Context context){
            this.context=context;
        }

        public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
            CancellationSignal cancellationSignal=new CancellationSignal();
            fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
        }


    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {


        this.update("there was an error "+errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
       this.update("Auth failed",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Help String : "+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("you can now access the app ",true);
    }

    private void update(String s, boolean b) {
        TextView descText=((Activity)context).findViewById(R.id.descText);
        ImageView fingerPrintIcon=((Activity)context).findViewById(R.id.fingerprintimage);

        descText.setText(s);
        if(b==true){
            descText.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            fingerPrintIcon.setImageResource(R.mipmap.ation_completed);

        }else{
            descText.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }
    }
    // there is more on Crypto Object
}
