package com.example.user.nutrisafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import datatypes.User;

public class MainActivity extends AppCompatActivity {

    User user;
    String name ;
    float age ;

    EditText nameID ;
    EditText ageID ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get views by id
        nameID =findViewById(R.id.name_et);
        ageID = findViewById(R.id.age_et);




    }


    public void gotoProfileActivity(View v)
    {
        //take user input
        //once user choose to go next
        //create user and send it to the profile intent
        name=nameID.getText().toString();
        age=Float.parseFloat(ageID.getText().toString());
        user = new User(name,age);

        Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
        i.putExtra("user",user);

        startActivity(i);
    }
}

