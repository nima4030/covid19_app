package sm.uok.api_project.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sm.uok.api_project.MainActivity;
import sm.uok.api_project.R;
import sm.uok.api_project.adapter.AdapterCountry;
import sm.uok.api_project.adapter.DBAdaptor;
import sm.uok.api_project.api_db.DataBase;
import sm.uok.api_project.model.Countries;
import sm.uok.api_project.model.List_sqlite;


public class CountryFragment extends Fragment {
    RecyclerView recyclerView_country;
    SpinKitView spin_kit;
    EditText search;
    ArrayList<Countries> listCountries=new ArrayList<>();
    AdapterCountry adapter;
    RequestQueue requestQueue;
    String api_url="https://api.covid19api.com/summary";
    LinearLayout visable;
    DataBase dataBase;
    ArrayList<List_sqlite> model;
    DBAdaptor dbAdaptor;





    public CountryFragment() {
        // Required empty public constructor
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_country, container, false);

        requestQueue= Volley.newRequestQueue(getContext());
        recyclerView_country=view.findViewById(R.id.recycle_country);
        spin_kit=view.findViewById(R.id.spin_kit);
        search=view.findViewById(R.id.search_country);
        visable=view.findViewById(R.id.visible);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // adapter.getFilter().filter(charSequence);
                /*if(charSequence.toString().equals("hi")){
                    Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                }*/

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                  dataBase=new DataBase(getContext());
                  Cursor r =dataBase.show();
                model=new ArrayList<List_sqlite>();
                  while (r.moveToNext()){
                      if(r.getString(1).contains(charSequence.toString().trim().toLowerCase())){

                          String name=r.getString(1);
                          String newConfirmed=r.getString(2);
                          String totalConfirmed=r.getString(3);
                          String newRecovered=r.getString(4);
                          String totalRecovered=r.getString(5);
                          String newDeaths=r.getString(6);
                          String totalDeaths=r.getString(7);

                          model.add(new List_sqlite(name,newConfirmed,totalConfirmed,newDeaths,totalDeaths,newRecovered,totalRecovered));






                      }
                      recyclerView_country.setLayoutManager(new GridLayoutManager(getContext(),2));
                      dbAdaptor=new DBAdaptor(getContext(),model);
                      recyclerView_country.setAdapter(dbAdaptor);



                  }





            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        StringRequest request=new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dataBase=new DataBase(getContext());
                    dataBase.deleteOFdb();
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray country=jsonObject.getJSONArray("Countries");


                    GsonBuilder gsonBuilder=new GsonBuilder();
                    Gson gson=gsonBuilder.create();
                    for(int i=0;i<country.length();i++){
                        Countries countries_=gson.fromJson(String.valueOf(country.getJSONObject(i)),Countries.class);
                        listCountries.add(countries_);
                        dataBase=new DataBase(getContext());
                        dataBase.insert(country.getJSONObject(i).getString("Country").toLowerCase(),country.getJSONObject(i).getString("NewConfirmed").toLowerCase(),
                                country.getJSONObject(i).getString("TotalConfirmed").toLowerCase(),country.getJSONObject(i).getString("NewRecovered").toLowerCase(),
                                country.getJSONObject(i).getString("TotalRecovered").toLowerCase(),country.getJSONObject(i).getString("NewDeaths").toLowerCase(),
                                country.getJSONObject(i).getString("TotalDeaths").toLowerCase());
                    }
                    adapter=new AdapterCountry(getContext(),listCountries);
                    recyclerView_country.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView_country.setAdapter(adapter);
                    visable.setVisibility(View.VISIBLE);
                    spin_kit.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        return view;
    }


}