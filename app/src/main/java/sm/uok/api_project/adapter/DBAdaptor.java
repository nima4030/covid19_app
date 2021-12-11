package sm.uok.api_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import sm.uok.api_project.ChartActivity;
import sm.uok.api_project.R;
import sm.uok.api_project.model.List_sqlite;

public class DBAdaptor extends RecyclerView.Adapter<DBAdaptor.Mvh> {
    Context context;
    ArrayList<List_sqlite> listSqlite;

    public DBAdaptor(Context context, ArrayList<List_sqlite> listSqlite) {
        this.context = context;
        this.listSqlite = listSqlite;
    }

    @NonNull
    @Override
    public DBAdaptor.Mvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_country,parent,false);
        return new Mvh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  DBAdaptor.Mvh holder, int position) {


        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");


        holder.NewConfirmed.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getNewConfirmed())));
        holder.TotalConfirmed.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getTotalConfirmed())));
        holder.NewRecovered.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getNewRecovered())));
        holder.TotalRecovered.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getTotalRecovered())));
        holder.NewDeaths.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getNewDeaths())));
        holder.TotalDeaths.setText(decimalFormat.format(Integer.valueOf(listSqlite.get(position).getTotalDeaths())));
        holder.name.setText(listSqlite.get(position).getCountry_name());


        /////////////on Click/////////////////
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChartActivity.class);
                intent.putExtra("NewConfirmed",listSqlite.get(position).getNewConfirmed());
                intent.putExtra("TotalConfirmed",listSqlite.get(position).getTotalConfirmed());
                intent.putExtra("NewRecovered",listSqlite.get(position).getNewRecovered());
                intent.putExtra("TotalRecovered",listSqlite.get(position).getTotalRecovered());
                intent.putExtra("NewDeaths",listSqlite.get(position).getNewDeaths());
                intent.putExtra("TotalDeaths",listSqlite.get(position).getTotalDeaths());
                intent.putExtra("name",listSqlite.get(position).getCountry_name());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listSqlite.size();
    }

    public class Mvh extends RecyclerView.ViewHolder {
        TextView name,NewConfirmed,TotalConfirmed,NewDeaths,TotalDeaths,NewRecovered,TotalRecovered;
        CardView root;



        public Mvh(@NonNull View itemView) {
            super(itemView);
            NewConfirmed=itemView.findViewById(R.id.NewConfirmed_c);
            TotalConfirmed=itemView.findViewById(R.id.TotalConfirmed_c);
            NewRecovered=itemView.findViewById(R.id.NewRecovered_c);
            TotalRecovered=itemView.findViewById(R.id.TotalRecovered_c);
            NewDeaths=itemView.findViewById(R.id.NewDeaths_c);
            TotalDeaths=itemView.findViewById(R.id.TotalDeaths_c);
            name=itemView.findViewById(R.id.name);
            root=itemView.findViewById(R.id.root);

             }
    }
}