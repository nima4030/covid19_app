package sm.uok.api_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;
import java.util.Random;

public class SplashScreen_Activity extends AppCompatActivity {
    private ProgressBar bar;
    private int counter = 0;
    private Button button;
    public Context context_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);
        context_s = getApplicationContext();
        ////////////////////////
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

            ////////////////////////
        Dexter.withContext(SplashScreen_Activity.this).withPermissions(Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            if(nInfo!=null){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(SplashScreen_Activity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }, 4000);

                            }else {
                                AlertDialog.Builder builder=new AlertDialog.Builder(SplashScreen_Activity.this);
                                builder.setTitle("خطا");
                                builder.setMessage("لطفا بعد اطمینان از اتصال به اینترنت دوباره اقدام کنید.");
                                builder.setPositiveButton("متوجه شدم", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finishAffinity();

                                    }
                                });
                                builder.create().show();
                            }


                        }else {
                            AlertDialog.Builder alert=new AlertDialog.Builder(SplashScreen_Activity.this);
                            alert.setTitle("خطا");
                            alert.setMessage("لطفا برای استفاده از برنامه دسترسی ها را تایید کنید");
                            alert.setPositiveButton("متوجه شدم", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finishAffinity();

                                }
                            });
                            alert.create().show();

                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

           /////////////////////////



        }

    }
