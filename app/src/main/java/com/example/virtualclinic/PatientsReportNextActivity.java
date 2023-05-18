package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.GettingReports;
import com.example.virtualclinic.Models.PatientPrescriptionDetail;
import com.example.virtualclinic.Models.PatientsDetailedPrescripedModel;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.PrescriptionAndAppointmnet;
import com.example.virtualclinic.databinding.ActivityPatientsReportNextBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientsReportNextActivity extends AppCompatActivity {
    ActivityPatientsReportNextBinding binding;
    List<PatientsDetailedPrescripedModel> prescriptionlist;
    int aptid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientsReportNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent()!=null)
        {
            prescriptionlist=  new Gson().fromJson(getIntent().getStringExtra("listOfPres"), new TypeToken<ArrayList<PatientsDetailedPrescripedModel>>() {
        }.getType());
            aptid=getIntent().getIntExtra("aptid",0);
        }
        String prescriptionText = "";
        if (prescriptionlist != null) {
            for (PatientsDetailedPrescripedModel prescription : prescriptionlist) {
                prescriptionText += "Medicine: " + prescription.getMyPatientP().getMedicine_name()+ "  " +
                        "Duration: " + prescription.getMyPatientP().getDuration() + "  " +
                        "Timing: " + prescription.getMyPatientP().getTimings() + "\n";
            }
            binding.textviewPrescription.setText(prescriptionText);
        } else {
            // Handle the case when prescriptions is null
            // You can display an appropriate message or take alternative actions
        }
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRetrofitInstance.getApiService().FinishDoneAppointment(aptid).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(PatientsReportNextActivity.this, "Finished", Toast.LENGTH_LONG).show();
                            finish();
                        }else
                            Toast.makeText(PatientsReportNextActivity.this,"Failed",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(PatientsReportNextActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}