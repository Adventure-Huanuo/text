package com.example.lee.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class travelapply extends AppCompatActivity {

    private static final String[] m = {"HomeTrip", "海外差旅", "商务差旅", "项目差旅"};
    private static final String[] n={"Home Trip-Homebase","Home Trip-居住地","Home Trip-家属","海外差旅-项目","海外差旅-非项目","海外差旅-培训","商务差旅-非项目","商务差旅-项目","商务差旅-培训","项目差旅","项目差旅-培训"};
    private Spinner spinner;
    private Spinner spinner_01;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter_01;
    private EditText startDate;
    private EditText endDate;
    private EditText fsDate;
    private Calendar calendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int eYear;
    private int eMonth;
    private int eDay;
    private RadioGroup radioGroup;
    private LinearLayout mPaper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travelapply);
        startDate=(EditText)findViewById(R.id.start_date);
        endDate=(EditText) findViewById(R.id.end_date) ;
        fsDate=(EditText) findViewById(R.id.flight_start_date);
        calendar=Calendar.getInstance();
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog=new DatePickerDialog(travelapply.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear=year;
                        mMonth=monthOfYear;
                        mDay=dayOfMonth;
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(year,monthOfYear,dayOfMonth);
                        startDate.setText(new StringBuilder().append(mYear).append("-").append((mMonth+1)<10?"0"+(mMonth+1):(mMonth+1)).append("-").append((mDay<10)?"0"+mDay:mDay));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog=new DatePickerDialog(travelapply.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eYear=year;
                        eMonth=monthOfYear;
                        eDay=dayOfMonth;
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(year,monthOfYear,dayOfMonth);
                        endDate.setText(new StringBuilder().append(eYear).append("-").append((eMonth+1)<10?"0"+(eMonth+1):(eMonth+1)).append("-").append((eDay<10)?"0"+eDay:eDay));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner_traveltype);
        spinner_01=(Spinner) findViewById(R.id.spinner_traveltype_det) ;
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m);
        adapter_01 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, n);
        //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        spinner.setAdapter(adapter);
        spinner_01.setAdapter(adapter_01);
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
        spinner_01.setVisibility(View.VISIBLE);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mPaper = (LinearLayout) findViewById(R.id.info);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                // TODO Auto-generated method stub
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) travelapply.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                if (rb.getText().equals("是")) {
                    mPaper.setVisibility(mPaper.VISIBLE);
                } else {
                    mPaper.setVisibility(mPaper.GONE);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initApartment() {
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("travelapply Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}