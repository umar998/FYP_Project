package com.example.virtualclinic.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualclinic.Models.SrDocAppointments;
import com.example.virtualclinic.R;
import com.example.virtualclinic.SrDocLoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Patients_Detail_Adapter extends RecyclerView.Adapter<Patients_Detail_Adapter.ViewHolder> {
    private List<SrDocAppointments> data=new ArrayList<>();
    public SrDocLoginActivity.AppointmentClick appointments;
    public Patients_Detail_Adapter(SrDocLoginActivity.AppointmentClick appointments) {
        this.appointments=appointments;
    }

    @NonNull
    @Override
    public Patients_Detail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_patients_detail, parent, false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Patients_Detail_Adapter.ViewHolder holder, int position) {

        holder.textView.setText("Appointment ID is "+data.get(holder.getAdapterPosition()).getAppointment_id());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.patient_name);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SrDocAppointments> newData) {
        data.clear();
        data = newData;

        notifyDataSetChanged();
    }
}

