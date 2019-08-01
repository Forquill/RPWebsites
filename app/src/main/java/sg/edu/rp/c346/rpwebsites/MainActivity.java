package sg.edu.rp.c346.rpwebsites;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btnGo;
    Spinner spnCat;
    Spinner spnSub;
    WebView wvMyPage;
    TextView tv1;
    TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.button);
        spnCat = findViewById(R.id.spinnerCat);
        spnSub = findViewById(R.id.spinnerSub);
        wvMyPage = findViewById(R.id.webViewPage);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);

        final ArrayList<String> alCat;
        final ArrayAdapter<String> aaCat;
        alCat = new ArrayList<>();
        final String[][] site = {
                {
                        "https://www.rp.edu.sg/",
                        "https://www.rp.edu.sg/student-life",
                },
                {
                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
                }
        };
        final String[] strRP = getResources().getStringArray(R.array.SubRP);
        final String[] strSOI = getResources().getStringArray(R.array.SubSOI);
        alCat.addAll(Arrays.asList(strRP));
        aaCat = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, alCat);
        spnSub.setAdapter(aaCat);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        alCat.clear();
                        alCat.addAll(Arrays.asList(strRP));
                        spnSub.setAdapter(aaCat);
                        break;
                    case 1:
                        alCat.clear();
                        alCat.addAll(Arrays.asList(strSOI));
                        spnSub.setAdapter(aaCat);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(spnCat.getSelectedItemPosition() == 0){
                    if(spnSub.getSelectedItemPosition() == 0){
                        //String url = "https://www.rp.edu.sg/";
                        String url = site[0][0];
                        wvMyPage.loadUrl(url);
                        btnGo.setVisibility(View.GONE);
                        tv1.setVisibility(View.GONE);
                        tv2.setVisibility(View.GONE);
                        spnSub.setVisibility(View.GONE);
                        spnCat.setVisibility(View.GONE);
                    }
                    else if(spnSub.getSelectedItemPosition() == 1){
                        //String url = "https://www.rp.edu.sg/student-life";
                        String url = site[0][1];
                        wvMyPage.loadUrl(url);
                        btnGo.setVisibility(View.GONE);
                        tv1.setVisibility(View.GONE);
                        tv2.setVisibility(View.GONE);
                        spnSub.setVisibility(View.GONE);
                        spnCat.setVisibility(View.GONE);
                    }
                }
                else if(spnCat.getSelectedItemPosition() == 1){
                    if(spnSub.getSelectedItemPosition() == 0){
                        //String url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                        String url = site[1][0];
                        wvMyPage.loadUrl(url);
                        btnGo.setVisibility(View.GONE);
                        tv1.setVisibility(View.GONE);
                        tv2.setVisibility(View.GONE);
                        spnSub.setVisibility(View.GONE);
                        spnCat.setVisibility(View.GONE);
                    }
                    else if(spnSub.getSelectedItemPosition() == 1){
                        //String url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                        String url = site[1][1];
                        wvMyPage.loadUrl(url);
                        btnGo.setVisibility(View.GONE);
                        tv1.setVisibility(View.GONE);
                        tv2.setVisibility(View.GONE);
                        spnSub.setVisibility(View.GONE);
                        spnCat.setVisibility(View.GONE);
                    }
                }*/
                String url = site[spnCat.getSelectedItemPosition()][spnSub.getSelectedItemPosition()];
                wvMyPage.loadUrl(url);
                btnGo.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                spnSub.setVisibility(View.GONE);
                spnCat.setVisibility(View.GONE);
            }
        });


    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("cat",spnCat.getSelectedItemPosition());
        prefEdit.putInt("sub",spnSub.getSelectedItemPosition());
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // put your code here...

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        spnCat.setSelection(prefs.getInt("cat",0));
        spnSub.setSelection(prefs.getInt("sub",0));
    }

}
