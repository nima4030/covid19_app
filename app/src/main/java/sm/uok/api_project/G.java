package sm.uok.api_project;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import sm.uok.api_project.fragment.HomeFragment;

public class G extends Application {
    String api_url="https://api.covid19api.com/summary";
    public RequestQueue requestQueue;
    public String newcon,totalcon,newrec,totalrec,newdeath,totaldeath;
    public static Context context;

    @Override
    public void onCreate() {

        super.onCreate();


    }
}
