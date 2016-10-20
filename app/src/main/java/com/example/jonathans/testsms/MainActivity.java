package com.example.jonathans.testsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> messages;
    private ArrayAdapter<String> adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list_view);
        messages = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_listview, messages);

        list.setAdapter(adapter);

        registerReceiver(broadcastReceiver, new IntentFilter("NEW_SMS"));
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String newMsg = (String)bundle.get("msg");
            if (isPostCode(newMsg) == true) {
                messages.add(newMsg + " - POSTCODE FOUND");
            }
            else {
                messages.add(newMsg);
            }
            adapter.notifyDataSetChanged();
        }
    };

    private boolean isPostCode(String str) {
        Pattern p = Pattern.compile("(?:[A-Za-z]\\d ?\\d[A-Za-z]{2})|(?:[A-Za-z][A-Za-z\\d]\\d ?\\d[A-Za-z]{2})|(?:[A-Za-z]{2}\\d{2} ?\\d[A-Za-z]{2})|(?:[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]{2})|(?:[A-Za-z]{2}\\d[A-Za-z] ?\\d[A-Za-z]{2})");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        else {
            return false;
        }
    }
}
