package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.virtualclinic.Models.Patient;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityUpdateNextScreenBinding;

import java.util.ArrayList;

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
        //String relation_name = i.getStringExtra("relation_name");
        String patientname = i.getStringExtra("patientname");
        String dob = i.getStringExtra("dob");
        String gender = i.getStringExtra("gender");
        //try {

        ArrayList<String> DataList = new ArrayList<>();
        DataList.add("Self");
        DataList.add("Spouse");
        DataList.add("Child 1");
        DataList.add("Child 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, DataList);
//            ArrayList<String> data = new ArrayList<>();
//
//
//            if(relation_name=="Spouse")
//            {
//                data.add("Spouse");
//                data.add("Self");
//                data.add("Child 1");
//                data.add("Child 2");
//            }
//
//            else if(relation_name=="Self")
//            {
//                data.add("Self");
//                data.add("Spouse");
//                data.add("Child 1");
//                data.add("Child 2");
//            }
//            else if(relation_name=="Child 1")
//            {
//                data.add("Child 1");
//                data.add("Self");
//                data.add("Spouse");
//                data.add("Child 2");
//            }
//            else
//            {
//                data.add("Child 2");
//                data.add("Self");
//                data.add("Spouse");
//                data.add("Child 1");
//            }
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(updateNextScreen.this,
//                android.R.layout.simple_spinner_item, data);

        binding.spinnerRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Self")) {
                    binding.edittextPatientname.setVisibility(View.GONE);
                } else if (selectedItem.equals("Spouse")) {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
                } else if (selectedItem.equals("Child 1")) {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
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
        // binding.spinnerRelation.setAdapter(adapter1);
        binding.edittextPatientname.setText(patientname);
        binding.edittextDOB.setText(dob);


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(binding.spinnerRelation.getSelectedItem()=="Self")
                {

                    String newcnic = binding.edittextCNIC.getText().toString();
                    String newfullname = binding.edittextFullname.getText().toString();
                    String newrelation_name = binding.spinnerRelation.getSelectedItem().toString();
                    String newpatient_name = "";
                    String newDOB = binding.edittextPatientname.getText().toString();
                    String genderr = "Male";
                    if (binding.RadiaButtonFeMale.isChecked())
                        genderr = "FeMale";
                    else
                        genderr = "Male";
                    RetrofitClient client =
                            RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    Patient p = new Patient();
                    //p.CNIC=newcnic;
                    p.full_name = newfullname;
                    p.relation = newrelation_name;
                    p.relative_name = newpatient_name;
                    p.DOB = newDOB;
                    p.Gender = genderr;
                    Toast.makeText(updateNextScreen.this,
                            "DOB :" + p.DOB,
                            Toast.LENGTH_SHORT).show();
                    Call<String> call = api.Updatepatdetails(p, patient_id, newcnic);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(updateNextScreen.this,
                                        "Updated",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(updateNextScreen.this, HomeFragment.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(updateNextScreen.this,
                                        "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(updateNextScreen.this,
                                    t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    String newcnic = binding.edittextCNIC.getText().toString();
                    String newfullname = binding.edittextFullname.getText().toString();
                    String newrelation_name = binding.spinnerRelation.getSelectedItem().toString();
                    String newpatient_name = binding.edittextPatientname.getText().toString();
                    String newDOB = binding.edittextPatientname.getText().toString();
                    String genderr = "Male";
                    if (binding.RadiaButtonFeMale.isChecked())
                        genderr = "FeMale";
                    else
                        genderr = "Male";
                    RetrofitClient client =
                            RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    Patient p = new Patient();
                    //p.CNIC=newcnic;
                    p.full_name = newfullname;
                    p.relation = newrelation_name;
                    p.relative_name = newpatient_name;
                    p.DOB = newDOB;
                    p.Gender = genderr;
                    Toast.makeText(updateNextScreen.this,
                            "DOB :" + p.DOB,
                            Toast.LENGTH_SHORT).show();
                    Call<String> call = api.Updatepatdetails(p, patient_id, newcnic);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(updateNextScreen.this,
                                        "Updated",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(updateNextScreen.this, HomeFragment.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(updateNextScreen.this,
                                        "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(updateNextScreen.this,
                                    t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        Toast.makeText(updateNextScreen.this, "Result" + patient_id, Toast.LENGTH_LONG).show();
    }
}