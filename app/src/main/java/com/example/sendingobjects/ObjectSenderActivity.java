package com.example.sendingobjects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sendingobjects.databinding.ActivityObjectSendingBinding;

import java.util.jar.Attributes;

public class ObjectSenderActivity extends AppCompatActivity {
          String  name;
        int type;
    String  gender;
    String RollNo;
    String PhoneNo;

   ActivityObjectSendingBinding b;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityObjectSendingBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        name = preferences.getString(Constants.STUDENT_NAME,"");
        RollNo = preferences.getString(Constants.ROLL_NO,"");
        PhoneNo = preferences.getString(Constants.PHONE_NUMBER,"");
        type = preferences.getInt(Constants.GENDER,0);

        b.editName.setText(name);
      //  deleteSharedPreferences(name);
      //  onDestroy();
        b.rollNo.setText(RollNo);
        b.PhoneNumber.setText(PhoneNo);
        b.gender.check(type);


        b.PhoneNumber.setOnEditorActionListener(editorActionListener);
        b.rollNo.setOnEditorActionListener(editorActionListener);
        b.editName.setOnEditorActionListener(editorActionListener);


    }



    // This method will run on click the save button...
    public void SaveTheDetails(View view) {

        ///Name of the student..
         name = b.editName.getText().toString().trim();
        if (name.isEmpty()){
            b.editName.setError("Please enter the name");
            return;

        }



        //type of button choose in the radio button..
         type = b.gender.getCheckedRadioButtonId();
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
         RollNo = b.rollNo.getText().toString().trim();
        if (RollNo.isEmpty()) {
            b.rollNo.setError("Please enter the roll no");
            return;
        }
       else if ( !RollNo.matches("(?!00)\\d{2}(([a-z]{4})|([A-Z]{4}))\\d{3,4}")){
           b.rollNo.setError("Enter correct rollno");
            return;
     }
             HideError();

       /// Phone No of the student..
         PhoneNo = b.PhoneNumber.getText().toString().trim();
        if (PhoneNo.isEmpty()){
            b.PhoneNumber.setError("Please  Enter the Number");
            return;
        }
        else if (!PhoneNo.matches("(0/91)?[7-9][0-9]{9}")){
            b.PhoneNumber.setError("Enter the correct phoneNo");

        }


              HideError();
       /// Make an intent..

    Intent intent = new Intent(ObjectSenderActivity.this, ObjectViewingActivity.class);
        Students students = new Students(name,RollNo,PhoneNo,gender);
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
    @Override
    protected void onPause() {
        super.onPause();
        ///  Create the shared prefernces object...
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit()
                .putString(Constants.STUDENT_NAME,name)
                .putString(Constants.ROLL_NO,RollNo)
                .putString(Constants.PHONE_NUMBER,PhoneNo)
                .putInt(Constants.GENDER,type)
                .apply();
    }

    /// Use of IME Options....
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId){
            case EditorInfo.IME_ACTION_NEXT:
                Toast.makeText(ObjectSenderActivity.this, "Next", Toast.LENGTH_SHORT).show();
                break;

                case EditorInfo.IME_ACTION_SEND:
                    Toast.makeText(ObjectSenderActivity.this, "Send", Toast.LENGTH_SHORT).show();
        }

        return false;
        }
        };


}




