package ubc.cs.cpsc210.sustainabilityapp;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * This is the main activity for the application, and hosts the three sub-activities which represent the
 * three tabs in the UI.
 */
public class UBCSustainabilityAppActivity extends TabActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        
        System.out.println("hello");
        
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, POIActivity.class);
        spec = tabHost.newTabSpec("poi").setIndicator("POI's", res.getDrawable(R.drawable.ic_tab_poi)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, FeatureActivity.class);
        spec = tabHost.newTabSpec("feature").setIndicator("Features", res.getDrawable(R.drawable.ic_tab_feature)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, MapDisplayActivity.class);
        spec = tabHost.newTabSpec("map").setIndicator("Map", res.getDrawable(R.drawable.ic_tab_map)).setContent(intent);
        tabHost.addTab(spec);
        
        // Set background of tabs to ubcblue. There seems to be no way reliably to get this to work in .xml config files
        for (int i = 0; i <= 2; i++) {
        	tabHost.getTabWidget().getChildTabViewAt(i).setBackgroundColor(getResources().getColor(R.color.ubcblue));
        }
        
        tabHost.setCurrentTab(2);
        
    }
}