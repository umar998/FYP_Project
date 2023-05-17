package com.example.virtualclinic.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualclinic.Models.GettingReports;
import com.example.virtualclinic.Models.SrDocAppointments;
import com.example.virtualclinic.R;
import com.example.virtualclinic.ReportsFragment;
import com.example.virtualclinic.SrDocLoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Patients_Reports_Adapter extends RecyclerView.Adapter<Patients_Reports_Adapter.ViewHolder>  {
    Context context;
    private List<GettingReports> data=new ArrayList<>();
    public ReportsFragment.AppointmentClick appointments;
    

    public Patients_Reports_Adapter(ReportsFragment.AppointmentClick appointments) {
        this.appointments=appointments;
    }

    @NonNull
    @Override
    public Patients_Reports_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_patients_report, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Patients_Reports_Adapter.ViewHolder holder, int position) {
        holder.textView.setText("Patient's name :"+data.get(holder.getAdapterPosition()).getPatientObject().getFull_name());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.textView.getContext(),holder.textView.getText().toString(), Toast.LENGTH_LONG).show();
                appointments.onAppointmentClicked(data.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.patient_name);
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<GettingReports> newData) {
        data.clear();
        data = newData;
        notifyDataSetChanged();
    }

}