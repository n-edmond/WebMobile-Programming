package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text
        FileOutputStream oStream;

        try {
            oStream = openFileOutput(FILENAME, Context.MODE_APPEND);//opens the files and appends info
            oStream.write(txt_content.getText().toString().getBytes());//add the inputted string to the text
            oStream.close();//close file
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        // Clear text input, hide keyboard. Just making easier to see
        txt_content.setText("");
        hideKeyboard(txt_content);

    }

    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text
        FileInputStream inStream;
        StringBuilder stringbuilt = new StringBuilder();


        inStream = openFileInput(FILENAME);//creating object to read file info
        InputStreamReader inputStreamReader = new InputStreamReader(inStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";

        while ((line = bufferedReader.readLine()) != null){
                stringbuilt.append(line);//while there is still info on the file, append it to the created strign.
        }

        inStream.close();
        contenttoDisplay.setText(stringbuilt);//show the string info
        contenttoDisplay.setVisibility(View.VISIBLE);

    }

    // Hides keyboard
    public void hideKeyboard(View v) {
        InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        iMM.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}
