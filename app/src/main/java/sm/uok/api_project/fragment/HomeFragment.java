package sm.uok.api_project.fragment;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import sm.uok.api_project.G;
import sm.uok.api_project.MainActivity;
import sm.uok.api_project.R;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }
    View view;
    TextView NewConfirmed,TotalConfirmed,NewDeaths,TotalDeaths,NewRecovered,TotalRecovered;
    RequestQueue requestQueue;
    String api_url="https://api.covid19api.com/summary";
    public static int G=0;
    private RelativeLayout parent;
    private SpinKitView spin_kit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);
        requestQueue= Volley.newRequestQueue(getContext());
        TotalConfirmed=view.findViewById(R.id.TotalConfirmed);
        NewDeaths=view.findViewById(R.id.NewDeaths);
        TotalDeaths=view.findViewById(R.id.TotalDeaths);
        NewRecovered=view.findViewById(R.id.NewRecovered);
        TotalRecovered=view.findViewById(R.id.TotalRecovered);
        NewConfirmed=view.findViewById(R.id.NewConfirmed);
        parent=view.findViewById(R.id.parent);
        spin_kit=view.findViewById(R.id.spin_kit);

        sm.uok.api_project.G g=new G();
        NewConfirmed.setText(g.newcon);
        TotalConfirmed.setText(g.totalcon);
        NewRecovered.setText(g.newrec);
        TotalRecovered.setText(g.totalrec);
        NewDeaths.setText(g.newdeath);
        TotalDeaths.setText(g.totaldeath);

        StringRequest request=new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject global=jsonObject.getJSONObject("Global");
                    DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    String NewConfirmed_=global.getString("NewConfirmed");
                    String newcon=decimalFormat.format(Integer.valueOf(NewConfirmed_));
                    NewConfirmed.setText(newcon);
                    String TotalConfirmed_=global.getString("TotalConfirmed");
                    String totalcon=decimalFormat.format(Integer.valueOf(TotalConfirmed_));
                    TotalConfirmed.setText(totalcon);
                    String NewRecovered_=global.getString("NewRecovered");
                    String newrec=decimalFormat.format(Integer.valueOf(NewRecovered_));
                    NewRecovered.setText(newrec);
                    String TotalRecovered_=global.getString("TotalRecovered");
                    String totalrec=decimalFormat.format(Integer.valueOf(TotalRecovered_));
                    TotalRecovered.setText(totalrec);
                    String NewDeaths_=global.getString("NewDeaths");
                    String newdeath=decimalFormat.format(Integer.valueOf(NewDeaths_));
                    NewDeaths.setText(newdeath);
                    String TotalDeaths_=global.getString("TotalDeaths");
                    String totaldeath=decimalFormat.format(Integer.valueOf(TotalDeaths_));
                    TotalDeaths.setText(totaldeath);

                    spin_kit.setVisibility(View.GONE);
                    parent.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR",error.getMessage()+"");
                G=0;

            }
        });
        requestQueue.add(request);
        return view;
    }
}