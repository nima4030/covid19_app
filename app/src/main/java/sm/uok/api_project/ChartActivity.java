package sm.uok.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class ChartActivity extends AppCompatActivity {
    TextView name_chart;
    Bundle bundle;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        name_chart=findViewById(R.id.name_chart);
        bundle=getIntent().getExtras();
        name_chart.setText(bundle.getString("name"));
        barChart=findViewById(R.id.barchart);

        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("TotalConfirmed")),Color.parseColor("#FF18AEA0")));
        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("NewConfirmed")),Color.parseColor("#FFFFEC47")));
        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("TotalRecovered")),Color.parseColor("#FF17D81F")));
        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("NewRecovered")),Color.WHITE));
        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("TotalDeaths")),Color.parseColor("#FF8F0813")));
        barChart.addBar(new BarModel("",Integer.valueOf(bundle.getString("NewDeaths")),Color.parseColor("#FF605F59")));
        barChart.startAnimation();






    }
    //public void onBackpre
}