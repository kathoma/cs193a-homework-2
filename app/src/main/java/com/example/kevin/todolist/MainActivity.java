package com.example.kevin.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tdArray;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tdArray = new ArrayList<String>();
        ListView tdList = (ListView) findViewById(R.id.tdListView);

        //Set adapter
        adapter = new ArrayAdapter<>(
                this,
                R.layout.todolistlayout,
                R.id.rowText,
                tdArray);
        tdList.setAdapter(adapter);

        //Set Event Listener
        tdList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> tdList,
                                                   View row,
                                                   int index,
                                                   long rowID) {

                        Log.wtf("LongPress","Long Click was performed");
                        tdArray.remove(index);
                        adapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }

        public void addButtonClick(View v) {
            EditText entryEditText = (EditText) findViewById(R.id.entryEditText);
            Button addButton = (Button) findViewById(R.id.addButton);
            ListView tdList = (ListView) findViewById(R.id.tdListView);
            if (entryEditText.getText().toString().length() > 0) {
                tdArray.add(entryEditText.getText().toString());
                entryEditText.setText("");
                adapter.notifyDataSetChanged();
                tdList.setSelection(adapter.getCount() - 1);
            }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putStringArrayList("tdArray",tdArray);
        }

        @Override
        protected void onRestoreInstanceState(Bundle inState){
            super.onRestoreInstanceState(inState);
            ArrayList<String> tdArray = inState.getStringArrayList("tdArray");
            adapter.addAll(tdArray);

        }
    }



