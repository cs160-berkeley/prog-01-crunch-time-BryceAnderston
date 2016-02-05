package com.example.user.crunchtime;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Establish existence of vars
    // ITEMS
    // Spinners for selecting exercises
    //
    Spinner spinner_exercise1;
    Spinner spinner_exercise2;
    // TextViews to declare whether the exercise is in minutes or repetitions
    TextView layout_repsmins1;
    TextView layout_repsmins2;
    // EditTexts to put values to calculate from in
    EditText edit_repsmins1;
    EditText edit_repsmins2;
    EditText edit_calories;
    // Radio buttons
    RadioButton radio_exercise1;
    RadioButton radio_exercise2;
    RadioButton radio_calories;
    String repsStatement;
    String minsStatement;
    int whichRadioChecked; //Can be 0, 1, 2
    String name_exercise1;
    String name_exercise2;
    // Dictionaries
    Hashtable<String, Double> dict_exercises_amount;
    Hashtable<String, String> dict_exercises_type;


    //END VARS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //MY SETUP CODE
        // ITEMS Vars
        // Spinners for selecting exercises
        //
        spinner_exercise1 = (Spinner) findViewById(R.id.spinner_exercise1);
        spinner_exercise2 = (Spinner) findViewById(R.id.spinner_exercise2);
        // TextViews to declare whether the exercise is in minutes or repetitions
        layout_repsmins1 = (TextView) findViewById(R.id.layout_repsmins1);
        layout_repsmins2 = (TextView) findViewById(R.id.layout_repsmins2);
        // EditTexts to put values to calculate from in
        edit_repsmins1 = (EditText) findViewById(R.id.edit_repsmins1);
        edit_repsmins2 = (EditText) findViewById(R.id.edit_repsmins2);
        edit_calories = (EditText) findViewById(R.id.edit_calories);
        // Radio buttons
        radio_exercise1 = (RadioButton) findViewById(R.id.radio_exercise1);
        radio_exercise2 = (RadioButton) findViewById(R.id.radio_exercise2);
        radio_calories = (RadioButton) findViewById(R.id.radio_calories);
        radio_exercise1.setChecked(true);
        // The CALCULATE! button is purely method-based

        //VARS
        //int numExercise1 = 0; //No need for these, bc just pull from TextViews
        //int numExercise1 = 0;
        //int numCalories = 0;

        repsStatement = " repetitions of ";
        minsStatement = " minutes of ";

        whichRadioChecked = 1; //Can be 0, 1, 2
        name_exercise1 = "Pushups"; //By default
        name_exercise2 = "Jogging"; //By default
        // Dictionaries
        dict_exercises_amount = new Hashtable<String, Double>();
        dict_exercises_type = new Hashtable<String, String>();


        //END VARS

        dict_exercises_amount.put("Pushups", 350.0);
        dict_exercises_amount.put("Situps", 200.0);
        dict_exercises_amount.put("Jumping Jacks", 10.0);
        dict_exercises_amount.put("Jogging", 12.0);
        dict_exercises_amount.put("Squats", 225.0);
        dict_exercises_amount.put("Leg-lifts", 25.0);
        dict_exercises_amount.put("Planks", 25.0);
        dict_exercises_amount.put("Pullups", 100.0);
        dict_exercises_amount.put("Cycling", 12.0);
        dict_exercises_amount.put("Walking", 20.0);
        dict_exercises_amount.put("Swimming", 13.0);
        dict_exercises_amount.put("Stair-climbing", 15.0);

        dict_exercises_type.put("Pushups", repsStatement);
        dict_exercises_type.put("Situps", repsStatement);
        dict_exercises_type.put("Jumping Jacks", minsStatement);
        dict_exercises_type.put("Jogging", minsStatement);
        dict_exercises_type.put("Squats", repsStatement);
        dict_exercises_type.put("Leg-lifts", minsStatement);
        dict_exercises_type.put("Planks", minsStatement);
        dict_exercises_type.put("Pullups", repsStatement);
        dict_exercises_type.put("Cycling", minsStatement);
        dict_exercises_type.put("Walking", minsStatement);
        dict_exercises_type.put("Swimming", minsStatement);
        dict_exercises_type.put("Stair-climbing", minsStatement);

        // Create Spinners to select exercises
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_exercise1.setAdapter(adapter1);

        // Second verse same as the first, let me at them and I'll rhyme on a dime
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_exercise2.setAdapter(adapter2);

        // Finish up with spinners
        spinner_exercise1.setOnItemSelectedListener(this);
        spinner_exercise2.setOnItemSelectedListener(this);

        //END ME
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ADDING ALL USEFUL METHODS
    // Radio buttons coding for choosing who to base off of
    public void onRadioButtonClicked(View view) {
        // Checks if the current button is checked
        boolean checked = ((RadioButton) view).isChecked();
        // Performs action based on checked button
        switch(view.getId()) {
            case R.id.radio_exercise1:
                if (checked) {
                    whichRadioChecked = 1;
                    break;
                }
            case R.id.radio_exercise2:
                if (checked) {
                    whichRadioChecked = 2;
                    break;
                }
            case R.id.radio_calories:
                if (checked) {
                    whichRadioChecked = 0;
                    break;
                }
        }
    }

    //Spinner Methods
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // Exercise selected. You can retrieve the selected item using
        //parent.getItemAtPosition(pos) (returns as an Object)
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner_exercise1)
        {
            name_exercise1 = (String) parent.getItemAtPosition(pos);
            layout_repsmins1.setText(dict_exercises_type.get(name_exercise1));
            radio_exercise1.setText(name_exercise1);
        }
        else if(spinner.getId() == R.id.spinner_exercise2)
        {
            name_exercise2 = (String) parent.getItemAtPosition(pos);
            layout_repsmins2.setText(dict_exercises_type.get(name_exercise2));
            radio_exercise2.setText(name_exercise2);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Spinner spinner = (Spinner) parent;

        if(spinner.getId() == R.id.spinner_exercise1)
        {
            spinner.setSelection(0);
            name_exercise1 = (String) parent.getItemAtPosition(0);
            layout_repsmins1.setText(dict_exercises_type.get(name_exercise1));
            radio_exercise1.setText(name_exercise1);
        }
        else if(spinner.getId() == R.id.spinner_exercise2)
        {
            spinner.setSelection(0);
            name_exercise2 = (String) parent.getItemAtPosition(0);
            layout_repsmins2.setText(dict_exercises_type.get(name_exercise2));
            radio_exercise2.setText(name_exercise2);
        }
    }

    // Click events for EditTexts
    public void editTextExer1_clicked(View view) {
        radio_exercise1.setChecked(true);
        radio_exercise2.setChecked(false);
        radio_calories.setChecked(false);
        whichRadioChecked = 1;
    }
    public void editTextExer2_clicked(View view) {
        radio_exercise1.setChecked(false);
        radio_exercise2.setChecked(true);
        radio_calories.setChecked(false);
        whichRadioChecked = 2;
    }
    public void editTextCalor_clicked(View view) {
        radio_exercise1.setChecked(false);
        radio_exercise2.setChecked(false);
        radio_calories.setChecked(true);
        whichRadioChecked = 0;
    }

    // The CALCULATE! button
    public void calculateAll(View view) {
        //String primary_exer;
        //String secondary_exer;
        Double base_rm;
        Double otherBase_rm;
        Double calories;
        Double exer1_rm;
        Double exer2_rm;
        if (whichRadioChecked == 1) {
            //Exercise 1 is the primary
            base_rm = dict_exercises_amount.get(name_exercise1);
            otherBase_rm = dict_exercises_amount.get(name_exercise2);
            exer1_rm = Double.parseDouble(edit_repsmins1.getText().toString());
            calories = (exer1_rm / base_rm)*100;
            exer2_rm = (calories*otherBase_rm)/100;
            edit_repsmins2.setText(""+exer2_rm);
            edit_calories.setText(""+calories);
        }
        else if (whichRadioChecked == 2) {
            //Exercise 2 is the primary
            base_rm = dict_exercises_amount.get(name_exercise2);
            otherBase_rm = dict_exercises_amount.get(name_exercise1);
            exer2_rm = Double.parseDouble(edit_repsmins2.getText().toString());
            calories = (exer2_rm / base_rm)*100;
            exer1_rm = (calories*otherBase_rm)/100;
            edit_repsmins1.setText(""+exer1_rm);
            edit_calories.setText(""+calories);
        }
        else if (whichRadioChecked == 0) {
            //Calories is the primary
            calories = Double.parseDouble(edit_calories.getText().toString());
            base_rm = dict_exercises_amount.get(name_exercise1);
            otherBase_rm = dict_exercises_amount.get(name_exercise2);
            exer1_rm = (calories*base_rm)/100;
            exer2_rm = (calories*otherBase_rm)/100;
            edit_repsmins1.setText(""+exer1_rm);
            edit_repsmins2.setText(""+exer2_rm);
        }
    }
}
