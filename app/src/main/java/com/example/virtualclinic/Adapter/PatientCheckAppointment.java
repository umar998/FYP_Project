package com.example.virtualclinic.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.virtualclinic.Models.patientsfullrecord;
import com.example.virtualclinic.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class PatientCheckAppointment extends RecyclerView.Adapter<PatientCheckAppointment.ViewHolder>  {
    Context context;
    

    public PatientCheckAppointment() {
    }

    @NonNull
    @Override
    public PatientCheckAppointment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientCheckAppointment.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    //private ArrayList<patientsfullrecord> booksList;

}