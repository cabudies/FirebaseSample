package com.example.firebasesample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText studentName, studentAddress, studentPhone;
    Button saveRecord;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());

        studentName = findViewById(R.id.enter_name);
        studentAddress = findViewById(R.id.enter_address);
        studentPhone = findViewById(R.id.enter_phone);
        saveRecord = findViewById(R.id.save_record);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        saveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentName.getText().toString();
                String address = studentAddress.getText().toString();
                long phone = Long.parseLong(studentPhone.getText().toString());

                // create hash map
                HashMap<String, Object> student = new HashMap<>();
                student.put("name", name);
                student.put("address", address);
                student.put("phone", phone);

                // save values to database
                databaseReference.push().setValue(student);

                Intent intent = new Intent();
                setResult(101, intent);
                finish();
            }
        });


    }
}
