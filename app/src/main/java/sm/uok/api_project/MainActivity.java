package sm.uok.api_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import sm.uok.api_project.api_db.DataBase;
import sm.uok.api_project.fragment.CountryFragment;
import sm.uok.api_project.fragment.HomeFragment;
import sm.uok.api_project.model.List_sqlite;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private BottomNavigationView navigation;
    DataBase dataBase;
    ArrayList<List_sqlite> listSqlite10;
    private boolean doubleback=false;
    private boolean check_country=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=findViewById(R.id.title);
        navigation=findViewById(R.id.navigation);
        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,homeFragment);
        fragmentTransaction.commit();
        title.setText("صفحه اصلی");




        ////////////////////////////فونت تغییر///////////////////////////////
        /*String Iran_font="Far_RoyaBd.ttf";
        AssetManager assetManager=this.getAssets();
        Typeface typeface=Typeface.createFromAsset(assetManager,Iran_font);
        try {
            Field field=Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            try {
                field.set(null,typeface);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }*/
        ////////////////////////////////////////////////////////////////////


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){

                    case R.id.home:
                        HomeFragment homeFragment=new HomeFragment();
                        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,homeFragment);
                        fragmentTransaction.commit();
                        title.setText("صفحه اصلی");
                        check_country=true;
                       break;
                    case R.id.country:
                        CountryFragment countryFragment =new CountryFragment();
                        FragmentTransaction fragmentTransaction_country=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction_country.replace(R.id.frameLayout,countryFragment);
                        fragmentTransaction_country.commit();
                        title.setText("کشور ها");
                        check_country=false;
                        break;
                   /* case R.id.data_b:
                        StringBuilder builder=new StringBuilder();
                        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("DB");
                        dialog.setPositiveButton("OK",null);
                        dataBase=new DataBase(MainActivity.this);
                        Cursor result =dataBase.show();
                        while (result.moveToNext()){
                            builder.append("ID : "+result.getString(0)+"\n");
                            builder.append("name : "+result.getString(1)+"\n");
                            builder.append("NewConfirmed : "+result.getString(2)+"\n");
                            builder.append("TotalConfirmed : "+result.getString(3)+"\n");
                            builder.append("NewRecovered : "+result.getString(4)+"\n");
                            builder.append("TotalRecovered : "+result.getString(5)+"\n");
                            builder.append("NewDeaths : "+result.getString(6)+"\n");
                            builder.append("TotalDeaths : "+result.getString(7)+"\n");


                        }

                        dialog.setMessage(builder);
                        dialog.create();
                        dialog.show();
                        break;*/
                }
                return true;
            }
        });



    }



  public void onBackPressed(){
      if(doubleback){
          finishAffinity();
          return;
      }else {
          doubleback=true;
          Toast.makeText(this,"برای خروج دوباره کلیک کنید",Toast.LENGTH_SHORT).show();
          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  doubleback=false;

              }
          },2000);
      }


  }
}