package com.example.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sendingobjects.databinding.ActivityObjectViewing3Binding;

public class ObjectViewingActivity extends AppCompatActivity {
    private ActivityObjectViewing3Binding b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b= ActivityObjectViewing3Binding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        ////Geeting the intent
         Intent intent = getIntent();

         //Validate data of student which is coming form the intent...
        Students students = (Students) intent.getSerializableExtra(Constants.STUDENT_KEY);

        // Showing data in the text fields...
      b.receivingName.setText(students.getName());
     b .receivingRollNo.setText(students.getRollno());
    b .receivingPhoneNo.setText(students.getPhno());


    }
    }




