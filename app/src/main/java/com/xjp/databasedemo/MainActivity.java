package com.xjp.databasedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private static final String DB_NAME = "demo.db";
    private static final int DB_VERSION = 1;
    private DBManager dbManager;
    private int index = 0;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this, DB_NAME, DB_VERSION, Person.class);

        tvResult = getView(R.id.tv_result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDataBase();
    }

    public void insert(View view) {
        index++;
        Person person = new Person("name" + index, 2, true);
        dbManager.insert(person);
    }

    public void update(View view) {
        ContentValues values = new ContentValues();
        values.put("age", 34);
        dbManager.updateById(Person.class, values, 1);
    }

    public void delete(View view) {
        int id = index--;
        dbManager.deleteById(Person.class, id);
    }

    public void findAll(View v) {
        List<Person> list = dbManager.findAll(Person.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = list.size(); i < size; i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        tvResult.setText(sb.toString());
    }

    public void findRecord(View v) {
        Person p = dbManager.findById(Person.class, 1);
        tvResult.setText(p.toString());
    }

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

}