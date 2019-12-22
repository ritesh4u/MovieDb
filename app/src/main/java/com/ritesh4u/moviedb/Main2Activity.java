package com.ritesh4u.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        table = (TableLayout) findViewById(R.id.mytable);
        try {
            showTableLayout();
        } catch (Exception e) {
            Log.e("TAG", "-----> " + e.toString());
        }
    }

    public void showTableLayout() throws Exception {

        JSONObject dummyRes = new JSONObject(dummyData);
        JSONArray headArray = dummyRes.getJSONArray("headData");
        JSONArray variantData = dummyRes.getJSONArray("variantData");
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();
        TableRow tr1 = new TableRow(this);
        TextView txtGeneric1 = new TextView(this);
        txtGeneric1.setWidth(24);
        tr1.addView(txtGeneric1);
        for (int i = 0; i < headArray.length(); i++) {
            TextView txtGeneric = new TextView(this);
            txtGeneric.setTextSize(18);
            txtGeneric.setText(headArray.getJSONObject(i).getString("name"));
            tr1.addView(txtGeneric);
        }
        table.addView(tr1);
        int rows = variantData.length();
        int colums = headArray.length();
        table.setStretchAllColumns(true);
        table.bringToFront();

        for (int i = 0; i < rows; i++) {

            TableRow tr = new TableRow(this);
            CheckBox txtGeneric2 = new CheckBox(this);
            txtGeneric2.setTag(variantData.getJSONObject(i).getString("id"));
            txtGeneric2.setWidth(24);

            tr.addView(txtGeneric2);
            for (int j = 0; j < colums; j++) {
                TextView txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);
                txtGeneric.setText(variantData.getJSONObject(i).getString("name_" + headArray.getJSONObject(j).getString("name")));
                tr.addView(txtGeneric);
                /*txtGeneric.setHeight(30); txtGeneric.setWidth(50);   txtGeneric.setTextColor(Color.BLUE);*/
            }
            table.addView(tr);
        }
    }


    String dummyData = "{\n" +
            "  \"status\": true,\n" +
            "  \"message\": \"Product variant added successfully.\",\n" +
            "  \"variantData\": [\n" +
            "    {\n" +
            "      \"id\": 140,\n" +
            "      \"head_Color\": \"Color\",\n" +
            "      \"name_Color\": \"Aqua\",\n" +
            "      \"head_test\": \"test\",\n" +
            "      \"name_test\": \"we\",\n" +
            "      \"head_ff\": \"ff\",\n" +
            "      \"name_ff\": \"fff\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 141,\n" +
            "      \"head_Color\": \"Color\",\n" +
            "      \"name_Color\": \"Aqua\",\n" +
            "      \"head_test\": \"test\",\n" +
            "      \"name_test\": \"qw\",\n" +
            "      \"head_ff\": \"ff\",\n" +
            "      \"name_ff\": \"fff\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 142,\n" +
            "      \"head_Color\": \"Color\",\n" +
            "      \"name_Color\": \"Beige\",\n" +
            "      \"head_test\": \"test\",\n" +
            "      \"name_test\": \"we\",\n" +
            "      \"head_ff\": \"ff\",\n" +
            "      \"name_ff\": \"fff\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 143,\n" +
            "      \"head_Color\": \"Color\",\n" +
            "      \"name_Color\": \"Beige\",\n" +
            "      \"head_test\": \"test\",\n" +
            "      \"name_test\": \"qw\",\n" +
            "      \"head_ff\": \"ff\",\n" +
            "      \"name_ff\": \"fff\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"headData\": [\n" +
            "    {\n" +
            "      \"name\": \"Color\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"test\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"ff\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"productID\": \"10\"\n" +
            "}\n";
}
