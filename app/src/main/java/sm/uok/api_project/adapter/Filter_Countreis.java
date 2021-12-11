package sm.uok.api_project.adapter;

import android.widget.Filter;

import java.util.ArrayList;

import sm.uok.api_project.model.Countries;

public class Filter_Countreis extends Filter {
    AdapterCountry adapterCountry;
    ArrayList<Countries> data;

    public Filter_Countreis(AdapterCountry adapterCountry, ArrayList<Countries> data) {
        this.adapterCountry = adapterCountry;
        this.data = data;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults=new FilterResults();
        if(charSequence!=null && charSequence.length()>0){
            charSequence=charSequence.toString().toUpperCase();
            ArrayList<Countries> filterData=new ArrayList<>();
            for(int i=0;i<data.size();i++){
                if(data.get(i).getCountry().toUpperCase().contains(charSequence)){
                    filterData.add(data.get(i));
                }


            }
            filterResults.count=filterData.size();
            filterResults.values=filterData;

        }else {
            filterResults.count=data.size();
            filterResults.values=data;

        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapterCountry.data=(ArrayList<Countries>)filterResults.values;
        adapterCountry.notifyDataSetChanged();

    }
}
