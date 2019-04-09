package com.example.firebasesample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        Log.d(TAG, "database values " + databaseReference);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d(TAG, "Data snapshot value: " + dataSnapshot);
                long values = dataSnapshot.getChildrenCount();
                for (int i = 0; i<values; i++) {
                    DataSnapshot data = dataSnapshot.child(String.valueOf(i));
                    Log.d(TAG, "data value: " + data);
                    String name = (String) data.child("name").getValue();
                    long phone = (long) data.child("phone").getValue();
                    String address = (String) data.child("address").getValue();
                    Log.d(TAG, "Student details are: " + name + phone + address);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }
}
