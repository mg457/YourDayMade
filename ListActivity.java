package com.example.maddie.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class ListActivity extends AppCompatActivity {

//    private static final String TAG = "Taglicious";
//    added to print curTask in console (debugging)

    static final int START_TASK_REQUEST = 1;
    private ArrayList<ItemAvg> items;
    private ArrayAdapter<ItemAvg> itemsAdapter;
    private ListView lvItems;
    private ItemAvg curItem;
    private Intent appInfo;
    private Task curTask;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        items = new ArrayList();
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instantiate list
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<ItemAvg>();
        itemsAdapter = new ArrayAdapter<ItemAvg>(this,
                android.R.layout.simple_list_item_1, items); //note: if not using ArrayList, need to make custom adapter
        lvItems.setAdapter(itemsAdapter);

        //clear data from previous runs of program
        itemsAdapter.clear();
        itemsAdapter.notifyDataSetChanged();

        //instantiate default data
        curTask = new Task(3, 3, 3, 3, 3);
        curItem = new ItemAvg("", 3, curTask);

        // handle when invidual task has been clicked upon
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // send data to EnterTask activity
                appInfo = new Intent(ListActivity.this, EnterTask.class);
                Bundle b = new Bundle();
                b.putSerializable("p", items.get(i));
                appInfo.putExtras(b);
                startActivityForResult(appInfo, START_TASK_REQUEST); //when EnterTask is finished, will automatically return to here
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
     * This method executes when the "ADD ITEM" button has been pressed
     */
    public void onAddItem(View v) {
        final EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString(); // TODO: handle when string entered is empty
        // add item with default settings
        curTask = new Task(3, 3, 3, 3, 3);
        curItem = new ItemAvg(itemText, 3, curTask);
        items.add(curItem);
        //sort the items in the list
        Collections.sort(items);
        itemsAdapter = new ArrayAdapter<ItemAvg>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        etNewItem.setText("");
        writeItems();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "List Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maddie.myapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "List Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.maddie.myapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == START_TASK_REQUEST) {
            // Make sure the request was successful
            Bundle b = data.getExtras();
            curItem = (ItemAvg) b.get("p");
            curTask = curItem.getTask();

            if (resultCode == RESULT_OK) {

                for (ItemAvg item : items) {
                    if (item.toString().equals(curItem.toString())) {
                        item.setAvg(curItem.getAvg());
                        item.setTask(curItem.getTask());
                        break;
                    }
                }
                Collections.sort(items);

                itemsAdapter = new ArrayAdapter<ItemAvg>(this,
                        android.R.layout.simple_list_item_1, items);
                lvItems.setAdapter(itemsAdapter);
            }


        }
    }


}

