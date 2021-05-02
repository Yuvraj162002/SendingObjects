package com.example.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sendingobjects.databinding.ActivityObjectSendingBinding;

public class ObjectSenderActivity extends AppCompatActivity {

    ActivityObjectSendingBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityObjectSendingBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
        // This method will run on click the save button...
    public void SaveTheDetails(View view) {

        ///Name of the student..
        String name = b.Name.getEditText().getText().toString().trim();
        if (name.isEmpty()){
            b.Name.setError("Please enter the name");
            return;
        }
                HideError();
       // Gender of the Student..
        String gender;

        //type of button choose in the radio button..
        int type = b.RadioGroup.getCheckedRadioButtonId();
        if(type == R.id.MaleRadiobt){
            gender = "MALE";
        }
        else if (type == R.id.FemaleRadiobt){
            gender = "FEMALE";
        }
        else {
            Toast.makeText(this, "Please choose the gender", Toast.LENGTH_SHORT).show();
            return;
        }



        //// Roll No of the Student..
        String RollNo = b.RollNo.getEditText().getText().toString().trim();
        if (RollNo.isEmpty()) {
            b.RollNo.setError("Please enter the roll no");
            return;
        }
       else if ( !RollNo.matches("(?!00)\\d{2}(([a-z]{4})|([A-Z]{4}))\\d{3,4}")){
           b.RollNo.setError("Enter correct rollno");
            return;
     }
             HideError();

       /// Phone No of the student..
        String PhoneNumber = b.PhoneNumber.getEditText().getText().toString().trim();
        if (PhoneNumber.isEmpty()){
            b.PhoneNumber.setError("Please  Enter the Number");
            return;
        }
        else if (!PhoneNumber.matches("(0/91)?[7-9][0-9]{9}")){
            b.PhoneNumber.setError("Enter the correct phoneNo");
        }
              HideError();
       /// Make an intent..

    Intent intent = new Intent(ObjectSenderActivity.this, ObjectViewingActivity.class);
        Students students = new Students(name,RollNo,PhoneNumber,gender);
        //put key in putExtra..
        intent.putExtra(Constants.STUDENT_KEY,students);
        startActivity(intent);

    }
    ///Hide method...to make null the all information...
    public  void HideError(){
        b.Name.setError(null);
        b.RollNo.setError(null);
        b.PhoneNumber.setError(null);
    }
}




