package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.PatientPrescriptionDetail;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.databinding.ActivitySearchingPatientCnicactivityBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchingPatientCNICActivity extends AppCompatActivity {
    ActivitySearchingPatientCnicactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchingPatientCnicactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchingPatientCNICActivity.this,
                        Login_Screen_Activity.class);
                startActivity(i);
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cnic= binding.edittextCNIC.getText().toString();
                GetRetrofitInstance.getApiService().GetAllPrescriptions(cnic).enqueue(new Callback<List<PatientPrescriptionDetail>>() {
                    @Override
                    public void onResponse(Call<List<PatientPrescriptionDetail>> call, Response<List<PatientPrescriptionDetail>> response) {
                        if (response.isSuccessful())
                        {
                            String prescriptionText = "";
                            List<PatientPrescriptionDetail> prescriptionDetails = response.body();
                            if (prescriptionDetails != null)
                            {
                                for (PatientPrescriptionDetail prescriptionDetail : prescriptionDetails)
                                {
                                    Prescription prescription = prescriptionDetail.getPrescription();
                                    prescriptionText += "Medicine: " + prescription.getMedicine() + "  " +
                                            "Duration: " + prescription.getDuration() + "  " +
                                            "Timing: " + prescription.getTiming() + "\n";
                                }
                                binding.textviewPrescription.setText(prescriptionText);

                            }
                            else {
                                Toast.makeText(SearchingPatientCNICActivity.this, "No history", Toast.LENGTH_LONG).show();
                                binding.textviewPrescription.setText("");
                            }
                        }
                        else {
                            Toast.makeText(SearchingPatientCNICActivity.this, "No history", Toast.LENGTH_LONG).show();
                            binding.textviewPrescription.setText("");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<PatientPrescriptionDetail>> call, Throwable t) {
                        binding.textviewPrescription.setText("");
                    }
                });
            }
        });
    }
}