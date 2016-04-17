package com.example.maddie.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class EnterTask extends AppCompatActivity {


    private ItemAvg inherited;
    private Task curTask;
    private Button doneButton;
    // TODO: add delete button functionality
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private SeekBar seekBar4;
    private SeekBar seekBar5;
    private TextView taskName; //text field where user enters input


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_task);

        //initialize buttons and slider bars
        doneButton = (Button) findViewById(R.id.doneButton);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        taskName = (TextView) findViewById(R.id.taskName);

        // retrieve data from ListActivity class
        Bundle b = this.getIntent().getExtras();
        inherited = (ItemAvg) b.get("p");
        curTask = inherited.getTask();
        //update display to reflect data retrieved
        seekBar1.setProgress(curTask.getQ1());
        seekBar2.setProgress(curTask.getQ2());
        seekBar3.setProgress(curTask.getQ3());
        seekBar4.setProgress(curTask.getQ4());
        seekBar5.setProgress(curTask.getQ5());
        ItemAvg name = (ItemAvg) getIntent().getExtras().getSerializable("p");
        taskName.setText(name.toString()); //overwrote toString method for ItemAvg; alternatively could write accessor method


        //handle when done button has been pressed
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retrieve data from user input
                curTask = new Task(seekBar1.getProgress(), seekBar2.getProgress(), seekBar3.getProgress(), seekBar4.getProgress(), seekBar5.getProgress());
                double result = curTask.getAverage();
                String name = (String) taskName.getText();

                // return data back to ListActivity class
                ItemAvg toReturn = new ItemAvg(name, result, curTask);
                Intent returnIntent = new Intent();
                Bundle br = new Bundle();
                br.putSerializable("p", toReturn);
                returnIntent.putExtras(br);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
