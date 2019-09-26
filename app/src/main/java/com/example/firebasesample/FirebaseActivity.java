package com.example.firebasesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    ProgressBar progressBar;

    ArrayList<StudentModel> studentModelArrayList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;

    int requestCode = 101;

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        FirebaseApp.initializeApp(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        textView = findViewById(R.id.message_tv);
        progressBar = findViewById(R.id.progress_bar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseActivity.this, MainActivity.class);
                startActivityForResult(intent, requestCode);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerAdapter = new RecyclerAdapter(this, studentModelArrayList);

        recyclerView.setAdapter(recyclerAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        Log.d(TAG, "database values " + databaseReference);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentModelArrayList.clear();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Log.d(TAG, "data value: " + data);
                    String name = (String) data.child("name").getValue();
                    long phone = Long.parseLong(String.valueOf(data.child("phone").getValue()));
                    String address = (String) data.child("address").getValue();
                    Log.d(TAG, "Student details are: " + name + phone + address);

                    studentModelArrayList.add(new StudentModel(name, address, phone));
                }

                recyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode) {
            Toast.makeText(getApplicationContext(), "Values added successfully", Toast.LENGTH_LONG).show();
        }
    }
}
