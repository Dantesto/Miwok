package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TastyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasty);
        fillListView();
    }

    private void fillListView() {
        List<TwoStrings> tastyList = makeTastyList();
        ListView listView = (ListView) findViewById(R.id.tasty_list);
        TwoStringsAdapter adapter = new TwoStringsAdapter(this, R.layout.tasty_list_item, tastyList);
        listView.setAdapter(adapter);
    }

    private ArrayList<TwoStrings> makeTastyList() {
        ArrayList<TwoStrings> tastyList = new ArrayList<TwoStrings>();
        for (int i = 0; i < 20; i++)
            tastyList.add(new TwoStrings("tasty" + i, "tastyDown" + i));
        return tastyList;
    }
}
