package com.example.sendingobjects;

import java.io.Serializable;
public class Students implements Serializable {
// private static long tSerialVersionUID = "1l";


  //  static final long serialVersionUID = 1L;
   String Name, rollno, phno;
    String gender;
 //  public  Students(){

  // }

    public Students(String name, String rollno, String phno, String gender) {
        Name = name;
        this.rollno = rollno;
        this.phno = phno;
        this.gender = gender;
    }


    ////get name..
    public String getName() {
        return Name;
    }

   ///get rollno..
    public String getRollno() {
        return rollno;
    }

    ///get phone number..
    public String getPhno() {
        return phno;
    }

    ///get gender..
    public String getGender() {
        return gender;
    }
}
