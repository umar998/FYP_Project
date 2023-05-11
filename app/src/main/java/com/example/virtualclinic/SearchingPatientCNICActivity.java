package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.databinding.ActivitySearchingPatientCnicactivityBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchingPatientCNICActivity extends AppCompatActivity {
    ActivitySearchingPatientCnicactivityBinding binding;
    List<Prescription> list;

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
                GetRetrofitInstance.getApiService().GetAllPrescriptions(cnic).enqueue(new Callback<List<Prescription>>() {
                    @Override
                    public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {
                        if (response.isSuccessful())
                        {
                            list=response.body();
                            Toast.makeText(SearchingPatientCNICActivity.this, "List"+list, Toast.LENGTH_LONG).show();
                            String prescriptionText = "";
                            if (list != null)
                            {
                                for (Prescription prescription : list)
                                {
                                   // Toast.makeText(SearchingPatientCNICActivity.this, "Medicine"+prescription.getMedicine(), Toast.LENGTH_LONG).show();
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
                    }

                    @Override
                    public void onFailure(Call<List<Prescription>> call, Throwable t) {

                    }
                });
            }
        });
    }
}