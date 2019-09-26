package com.example.firebasesample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StudentViewHolder> {

    ArrayList<StudentModel> studentModelArrayList = new ArrayList<>();
    Context context;

    public RecyclerAdapter(Context context, ArrayList<StudentModel> studentModelArrayList){
        this.context = context;
        this.studentModelArrayList = studentModelArrayList;
    }


    @NonNull
    @Override
    public RecyclerAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        StudentViewHolder viewHolder = new StudentViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.StudentViewHolder studentViewHolder, int i) {

        StudentModel studentModel = this.studentModelArrayList.get(i);

        studentViewHolder.studentName.setText(studentModel.getStudentName());
        studentViewHolder.studentAddress.setText(studentModel.getStudentAddress());
        studentViewHolder.studentPhone.setText(String.valueOf(studentModel.getStudentPhone()));
    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView studentName, studentAddress, studentPhone;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.item_list_student_name);
            studentAddress = itemView.findViewById(R.id.item_list_student_address);
            studentPhone = itemView.findViewById(R.id.item_list_student_phone);
        }
    }
}
