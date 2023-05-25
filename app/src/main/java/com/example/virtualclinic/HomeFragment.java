package com.example.virtualclinic;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.virtualclinic.Models.Patient;
import com.example.virtualclinic.Models.PatientObject;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.FragmentHomeBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    public MenuItem menuItem;
    SearchView searchView;
    TextInputEditText CNIC, fullname, patientname, dob;
    int patientId = -1;
    int id=StaticClass.id;
    StaticClass s;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent i = getActivity().getIntent();
        int nurseID = i.getIntExtra("nurseID", 0);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button b = view.findViewById(R.id.save);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        ArrayList<String> DataList = new ArrayList<>();
        DataList.add("Self");
        DataList.add("Spouse");
        DataList.add("Child 1");
        DataList.add("Child 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, DataList);
        binding.spinnerRelation.setAdapter(adapter);
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!binding.edittextCNIC.getText().toString().isEmpty()) {
                    Intent i = new Intent(getActivity(), updateNextScreen.class);
                    i.putExtra("patient_id", patientId);
                    i.putExtra("cnic", binding.edittextCNIC.getText().toString());
                    i.putExtra("full_name", binding.edittextFulname.getText().toString());
                    i.putExtra("patientname", binding.edittextPatientname.getText().toString());
                    i.putExtra("dob", binding.edittextDOB.getText().toString());
                    startActivity(i);
                }

            }
        });

        binding.save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Patient p = new Patient();
                    p.setCNIC(binding.edittextCNIC.getText().toString());
                    p.setFull_name(binding.edittextFulname.getText().toString());
                    p.setRelation((String) binding.spinnerRelation.getSelectedItem());
                        p.setRelative_name(binding.edittextPatientname.getText().toString());
                    p.setDOB(binding.edittextDOB.getText().toString());
                    p.setGender(binding.genderRB.getCheckedRadioButtonId() == R.id.Radia_button_Male ? "Male" : "FeMale");

                    RetrofitClient.getInstance().getMyApi().Addpatient(p).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()){
                                id = Integer.parseInt(response.body());
                                Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                i.putExtra("staticclass", id);
                                i.putExtra("nurseID", nurseID);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });

        binding.addvitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                i.putExtra("staticclass", id);
                i.putExtra("nurseID", nurseID);
                startActivity(i);
            }
        });


        binding.edittextCNIC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                GetRetrofitInstance.getApiService().Checkcnic(s.toString()).enqueue(new Callback<PatientObject>() {
                    @Override
                    public void onResponse(Call<PatientObject> call, Response<PatientObject> response) {

                        if (response.isSuccessful()) {
                            PatientObject p = response.body();
                            binding.edittextFulname.setText(p.getFull_name());
                            if (binding.spinnerRelation.getAdapter() != null) {
                                try {
                                    if (p.getRelation().equals("Self"))
                                        binding.spinnerRelation.setSelection(0);
                                    else if (p.getRelation().equals("Spouse"))
                                        binding.spinnerRelation.setSelection(1);
                                    else if (p.getRelation().equals("Child 1"))
                                        binding.spinnerRelation.setSelection(2);
                                    else if (p.getRelation().equals("Child 2"))
                                        binding.spinnerRelation.setSelection(3);
                                } catch (Exception e) {
                                    Log.d("spException", "relation unable to set");
                                }
                            }
                            id=p.getPatient_id();
                            if (p.getRelative_name() != null && !p.getRelative_name().equals(""))
                                binding.edittextPatientname.setText(p.getRelative_name());
                            binding.edittextDOB.setText(p.getDob());
                            if (p.getGender().equals("Male"))
                                binding.RadiaButtonMale.isChecked();
                            else binding.RadiaButtonFeMale.isChecked();
                        }
                        else
                        {
                            binding.edittextFulname.setText("");
                            //binding.spinnerRelation("");
                            binding.edittextPatientname.setText("");
                            binding.edittextDOB.setText("");
                        }



          /*              if (response.isSuccessful()) {
                            PatientObject p = response.body();
                            patientId = response.body().getPatient_id();
                            String cnic = response.body().getCnic();
                            String full_name = response.body().getFull_name();
                            ArrayList<String> data = new ArrayList<>();
                            data.add(response.body().getRelation());
                            if (response.body().getRelation().equals("Self")) {
                                data.add("Spouse");
                                data.add("Child 1");
                                data.add("Child 2");
                            } else if (response.body().getRelation().equals("Spouse")) {
                                data.add("Self");
                                data.add("Child 1");
                                data.add("Child 2");
                            } else if (response.body().getRelation().equals("Child 1")) {
                                data.add("Self");
                                data.add("Spouse");
                                data.add("Child 2");
                            } else if (response.body().getRelation().equals("Child 2")) {
                                data.add("Self");
                                data.add("Spouse");
                                data.add("Child 1");
                            }
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(),
                                    android.R.layout.simple_spinner_item, data);
                            String patientname = response.body().getRelative_name();
                            String dob = response.body().getDob();
                            String gender = response.body().getGender();
                            binding.edittextFulname.setText(full_name);
                            binding.spinnerRelation.setAdapter(adapter1);
                            binding.edittextPatientname.setText(patientname);
                            binding.edittextDOB.setText(dob);

                            binding.save.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (binding.spinnerRelation.getSelectedItem() == "Self") {
                                        String cnic = binding.edittextCNIC.getText().toString();
                                        String Fullname = binding.edittextFulname.getText().toString();
                                        String relation_name = binding.spinnerRelation.getSelectedItem().toString();
                                        String patient_name = "";
                                        String DOB = binding.edittextDOB.getText().toString();
                                        String gender = "Male";
                                        if (binding.RadiaButtonFeMale.isChecked())
                                            gender = "FeMale";
                                        else
                                            gender = "Male";
                                        RetrofitClient client =
                                                RetrofitClient.getInstance();
                                        Api api = client.getMyApi();
                                        Patient p = new Patient();
                                        p.CNIC = cnic;
                                        p.full_name = Fullname;
                                        p.relation = relation_name;
                                        p.relative_name = patient_name;
                                        p.DOB = DOB;
                                        p.Gender = gender;
                                        api.Addpatient(p).enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                if (response.isSuccessful()) {
                                                    StaticClass.id = Integer.parseInt(response.body());
                                                    Toast.makeText(requireContext(),
                                                            "Saved  " + StaticClass.id,
                                                            Toast.LENGTH_SHORT).show();
                                                    //                mainActivity=(MainActivity)getActivity();
                                                    Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                                    i.putExtra("staticclass", StaticClass.id);
                                                    i.putExtra("nurseID", nurseID);
//                                i.putExtra("cnic",p.CNIC);
//                                i.putExtra("Fullname",p.full_name);
//                                i.putExtra("relation_name",p.relation);
//                                i.putExtra("patient_name",p.relative_name);
//                                i.putExtra("DOB",p.DOB);
//                                i.putExtra("gender",p.Gender);
                                                    startActivity(i);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                            }
                                        });
                                    } else {
                                        String cnic = binding.edittextCNIC.getText().toString();
                                        String Fullname = binding.edittextFulname.getText().toString();
                                        String relation_name = binding.spinnerRelation.getSelectedItem().toString();
                                        String patient_name = binding.edittextPatientname.getText().toString();
                                        String DOB = binding.edittextDOB.getText().toString();
                                        String gender = "Male";
                                        if (binding.RadiaButtonFeMale.isChecked())
                                            gender = "FeMale";
                                        else if (binding.RadiaButtonMale.isChecked())
                                            gender = "male";
                                        RetrofitClient client =
                                                RetrofitClient.getInstance();
                                        Api api = client.getMyApi();
                                        Patient p = new Patient();
                                        p.CNIC = cnic;
                                        p.full_name = Fullname;
                                        p.relation = relation_name;
                                        p.relative_name = patient_name;
                                        p.DOB = DOB;
                                        p.Gender = gender;
                                        api.Addpatient(p).enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                if (response.isSuccessful()) {
                                                    StaticClass.id = Integer.parseInt(response.body());
                                                    Toast.makeText(requireContext(),
                                                            "Saved  " + StaticClass.id,
                                                            Toast.LENGTH_SHORT).show();
                                                    //                mainActivity=(MainActivity)getActivity();
                                                    Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                                    i.putExtra("staticclass", StaticClass.id);
                                                    i.putExtra("nurseID", nurseID);
//                                i.putExtra("cnic",p.CNIC);
//                                i.putExtra("Fullname",p.full_name);
//                                i.putExtra("relation_name",p.relation);
//                                i.putExtra("patient_name",p.relative_name);
//                                i.putExtra("DOB",p.DOB);
//                                i.putExtra("gender",p.Gender);
                                                    startActivity(i);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {

                                            }
                                        });
                                    }

                                }
                            });

//                            ArrayList<String> data1= new ArrayList<>();
//                            data1.add(response.body().getRelation());


                        } else {
                            ArrayList<String> data = new ArrayList<>();
                            data.add("Self");
                            data.add("Spouse");
                            data.add("Child 1");
                            data.add("Child 2");
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(),
                                    android.R.layout.simple_spinner_item, data);
                            binding.edittextFulname.setText("");
                            binding.spinnerRelation.setAdapter(adapter1);
                            binding.edittextPatientname.setText("");
                            binding.edittextDOB.setText("");
                        }*/
                    }

                    @Override
                    public void onFailure(Call<PatientObject> call, Throwable t) {
                        binding.edittextFulname.setText("");
                        //binding.spinnerRelation("");
                        binding.edittextPatientname.setText("");
                        binding.edittextDOB.setText("");
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private boolean validate() {
        if (Objects.equals(binding.edittextCNIC.getText().toString(), "")) {
            Toast.makeText(requireContext(), "Please enter cnic", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(binding.edittextCNIC.getText().toString().length()<13){
            Toast.makeText(requireContext(), "Please Valid cnic", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Objects.equals(binding.edittextFulname.getText().toString(), "")) {
            Toast.makeText(requireContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.spinnerRelation.getSelectedItem() != "Self" && binding.edittextPatientname.getText().toString().equals("")) {
            Toast.makeText(requireContext(), "Please enter patient name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edittextDOB.getText().toString().equals("")) {
            Toast.makeText(requireContext(), "Please enter date of birth", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;

    }
}