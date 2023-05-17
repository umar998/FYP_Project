package com.example.virtualclinic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualclinic.Models.Patient;
import com.example.virtualclinic.databinding.ActivityUpdateNextScreenBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateNextScreen extends AppCompatActivity {
    ActivityUpdateNextScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNextScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();

        int patient_id = i.getIntExtra("patient_id", 0);
        String cnic = i.getStringExtra("cnic");
        String full_name = i.getStringExtra("full_name");
        String patientname = i.getStringExtra("patientname");
        String dob = i.getStringExtra("dob");
        //try {

        ArrayList<String> DataList = new ArrayList<>();
        DataList.add("Self");
        DataList.add("Spouse");
        DataList.add("Child 1");
        DataList.add("Child 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, DataList);


        binding.spinnerRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if ("Self".equals(selectedItem)) {
                    binding.edittextPatientname.setVisibility(View.GONE);
                } else {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.spinnerRelation.setAdapter(adapter);
        binding.edittextCNIC.setText(cnic);
        binding.edittextFullname.setText(full_name);
        binding.edittextPatientname.setText(patientname);
        binding.edittextDOB.setText(dob);

        binding.save.setOnClickListener(view -> {
            Patient p = new Patient();
            p.setCNIC(Objects.requireNonNull(binding.edittextCNIC.getText()).toString());
            p.setDOB(Objects.requireNonNull(binding.edittextDOB.getText()).toString());
            p.setFull_name(Objects.requireNonNull(binding.edittextFullname.getText()).toString());
            p.setGender(binding.genderRB.getCheckedRadioButtonId() == R.id.Radia_button_Male ? "Male" : "Female");
            p.setRelative_name(getRelation());
            p.setRelation(binding.spinnerRelation.getSelectedItem().toString());


            GetRetrofitInstance.getApiService().Updatepatdetails(p, patient_id
                    , binding.edittextCNIC.getText().toString()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(updateNextScreen.this,
                                "Updated",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(updateNextScreen.this,
                                "Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    Log.e("err", t.getMessage());
                }
            });
        });

    }

    private String getRelation() {
        if (binding.spinnerRelation.getSelectedItem() == "Self")
            return "";
        else if (binding.spinnerRelation.getSelectedItem() == "Spouse" ||
                binding.spinnerRelation.getSelectedItem() == "Child 1" ||
                binding.spinnerRelation.getSelectedItem() == "SpouChild 2") {
            return Objects.requireNonNull(binding.edittextPatientname.getText()).toString();
        }
        return "";
    }
}