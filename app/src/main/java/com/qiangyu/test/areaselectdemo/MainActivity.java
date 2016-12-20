package com.qiangyu.test.areaselectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView areaTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        areaTv = (TextView) findViewById(R.id.area_tv);
    }

    public void select(View view) {
        Intent intent = new Intent(this, AreaSelectActivity.class);
        startActivityForResult(intent, 100);
    }

    private Pattern intPattern = Pattern.compile("^[-\\+]?[\\d]*\\.0*$");

    public String getString(Map map, String key, String defaultValue) {
        Object obj = map.get(key);
        return obj == null ? defaultValue : (obj instanceof Number && intPattern.matcher(obj.toString()).matches() ? String.valueOf(Long.valueOf(((Number) obj).longValue())) : obj.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 选择地区返回h
         */
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            final Map map = (Map) data.getSerializableExtra("addressInfo");
            String areaName = String.format("%s %s %s", getString(map, "provName", ""),
                    getString(map, "cityName", ""), getString(map, "districtName", ""));
            areaTv.setText(areaName);
        }


    }
}
