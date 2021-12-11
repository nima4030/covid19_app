package sm.uok.api_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import sm.uok.api_project.ChartActivity;
import sm.uok.api_project.R;
import sm.uok.api_project.interfaces.ItemClickListener;
import sm.uok.api_project.model.Countries;

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<Countries> data;
    ArrayList<Countries> data_filter;
    Filter_Countreis filter_countreis;

    public AdapterCountry(Context context, ArrayList<Countries> data) {
        this.context = context;
        this.data = data;
        this.data_filter=data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCountry.MyViewHolder holder, int position) {

        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");


        holder.NewConfirmed.setText(decimalFormat.format(Integer.valueOf(data.get(position).getNewConfirmed())));
        holder.TotalConfirmed.setText(decimalFormat.format(Integer.valueOf(data.get(position).getTotalConfirmed())));
        holder.NewRecovered.setText(decimalFormat.format(Integer.valueOf(data.get(position).getNewRecovered())));
        holder.TotalRecovered.setText(decimalFormat.format(Integer.valueOf(data.get(position).getTotalRecovered())));
        holder.NewDeaths.setText(decimalFormat.format(Integer.valueOf(data.get(position).getNewDeaths())));
        holder.TotalDeaths.setText(decimalFormat.format(Integer.valueOf(data.get(position).getTotalDeaths())));
        holder.name.setText(data.get(position).getCountry());

        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChartActivity.class);
                intent.putExtra("NewConfirmed",data.get(position).getNewConfirmed());
                intent.putExtra("TotalConfirmed",data.get(position).getTotalConfirmed());
                intent.putExtra("NewRecovered",data.get(position).getNewRecovered());
                intent.putExtra("TotalRecovered",data.get(position).getTotalRecovered());
                intent.putExtra("NewDeaths",data.get(position).getNewDeaths());
                intent.putExtra("TotalDeaths",data.get(position).getTotalDeaths());
                intent.putExtra("name",data.get(position).getCountry());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        if(filter_countreis==null){
            filter_countreis=new Filter_Countreis(this,data_filter);
        }
        return filter_countreis;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,NewConfirmed,TotalConfirmed,NewDeaths,TotalDeaths,NewRecovered,TotalRecovered;


        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            NewConfirmed=itemView.findViewById(R.id.NewConfirmed_c);
            TotalConfirmed=itemView.findViewById(R.id.TotalConfirmed_c);
            NewRecovered=itemView.findViewById(R.id.NewRecovered_c);
            TotalRecovered=itemView.findViewById(R.id.TotalRecovered_c);
            NewDeaths=itemView.findViewById(R.id.NewDeaths_c);
            TotalDeaths=itemView.findViewById(R.id.TotalDeaths_c);
            name=itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view);
        }
    }
}

