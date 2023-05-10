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
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    public MenuItem menuItem;
    SearchView searchView;
    TextInputEditText CNIC , fullname , patientname, dob;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        Button b=view.findViewById(R.id.save);
        binding= FragmentHomeBinding.inflate(getLayoutInflater());
        ArrayList<String> DataList = new ArrayList<>();
        DataList.add("Self");
        DataList.add("Spouse");
        DataList.add("Child 1");
        DataList.add("Child 2");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,DataList);
        binding.spinnerRelation.setAdapter(adapter);

        binding.edittextCNIC.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String cnic = binding.edittextCNIC.getText().toString();
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
                Call<PatientObject> call= api.Checkcnic(cnic);
                call.enqueue(new Callback<PatientObject>()
                {
                    @Override
                    public void onResponse(Call<PatientObject> call, Response<PatientObject> response)
                    {
                        if(response.isSuccessful())
                        {
                            PatientObject p=response.body();
                            int patient_id=response.body().getPatient_id();
                            String cnic=response.body().getCnic();
                            String full_name=response.body().getFull_name();
                            ArrayList<String> data= new ArrayList<>();
                            data.add(response.body().getRelation());
                            if(response.body().getRelation().equals("Self"))
                            {
                                data.add("Spouse");
                                data.add("Child 1");
                                data.add("Child 2");
                            }
                            else if(response.body().getRelation().equals("Spouse"))
                            {
                                data.add("Self");
                                data.add("Child 1");
                                data.add("Child 2");
                            }
                            else if(response.body().getRelation().equals("Child 1"))
                            {
                                data.add("Self");
                                data.add("Spouse");
                                data.add("Child 2");
                            }
                            else if(response.body().getRelation().equals("Child 2"))
                            {
                                data.add("Self");
                                data.add("Spouse");
                                data.add("Child 1");
                            }
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(),
                                    android.R.layout.simple_spinner_item, data);
                            String patientname= response.body().getRelative_name();
                            String dob= response.body().getDob();
                            String gender=response.body().getGender();
                            binding.edittextFulname.setText(full_name);
                            binding.spinnerRelation.setAdapter(adapter1);
                            binding.edittextPatientname.setText(patientname);
                            binding.edittextDOB.setText(dob);

                            binding.save.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    if(binding.spinnerRelation.getSelectedItem()=="Self")
                                    {
                                        String cnic = binding.edittextCNIC.getText().toString();
                                        String Fullname = binding.edittextFulname.getText().toString();
                                        String relation_name=binding.spinnerRelation.getSelectedItem().toString();
                                        String patient_name="";
                                        String DOB=binding.edittextDOB.getText().toString();
                                        String gender = "Male";
                                        if(binding.RadiaButtonFeMale.isChecked())
                                            gender = "FeMale";
                                        else
                                            gender = "Male";
                                        RetrofitClient client =
                                                RetrofitClient.getInstance();
                                        Api api = client.getMyApi();
                                        Patient p = new Patient();
                                        p.CNIC=cnic;
                                        p.full_name=Fullname;
                                        p.relation=relation_name;
                                        p.relative_name=patient_name;
                                        p.DOB=DOB;
                                        p.Gender=gender;
                                        api.Addpatient(p).enqueue(new Callback<String>()
                                        {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response)
                                            {
                                                if(response.isSuccessful()) {
                                                    StaticClass.id=Integer.parseInt(response.body());
                                                    Toast.makeText(requireContext(),
                                                            "Saved  "+ StaticClass.id,
                                                            Toast.LENGTH_SHORT).show();
                                                    //                mainActivity=(MainActivity)getActivity();
                                                    Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                                    i.putExtra("staticclass", StaticClass.id);
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
                                            public void onFailure(Call<String> call, Throwable t)
                                            {
                                            }
                                        });
                                    }
                                    else
                                    {
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
                                        api.Addpatient(p).enqueue(new Callback<String>()
                                        {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response)
                                            {
                                                if(response.isSuccessful())
                                                {
                                                    StaticClass.id=Integer.parseInt(response.body());
                                                    Toast.makeText(requireContext(),
                                                            "Saved  "+ StaticClass.id,
                                                            Toast.LENGTH_SHORT).show();
                                                    //                mainActivity=(MainActivity)getActivity();
                                                    Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                                    i.putExtra("staticclass", StaticClass.id);
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
                                            public void onFailure(Call<String> call, Throwable t)
                                            {

                                            }
                                        });
                                    }

                                }
                            });

//                            ArrayList<String> data1= new ArrayList<>();
//                            data1.add(response.body().getRelation());

                            Intent i = new Intent(getActivity(), updateNextScreen.class);
                            i.putExtra("patient_id",patient_id);
                            i.putExtra("cnic",cnic);
                            i.putExtra("full_name",full_name);
                            i.putExtra("relation_name",data);
                            i.putExtra("patientname",patientname);
                            i.putExtra("dob",dob);
                            i.putExtra("gender",gender);

                            binding.update.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v) {

                                    startActivity(i);

                                }
                            });
                        }
                        else
                        {
                            ArrayList<String> data= new ArrayList<>();
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
                        }
                    }
                    @Override
                    public void onFailure(Call<PatientObject> call, Throwable t)
                    {
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
        binding.spinnerRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Self")) {
                    binding.edittextPatientname.setVisibility(View.GONE);
                }
                else if(selectedItem.equals("Spouse")) {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
                }
                else if(selectedItem.equals("Child 1")) {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
                }
                else {
                    binding.edittextPatientname.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // Inflate the layout for this fragment
        binding.save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                if (TextUtils.isEmpty(CNIC.getText().toString()) || (TextUtils.isEmpty(fullname.getText().toString()) || (TextUtils.isEmpty(dob.getText().toString())) )){
//                    Toast.makeText(requireContext(), "Required Fields are Empty", Toast.LENGTH_LONG).show();
//                }
                if(binding.spinnerRelation.getSelectedItem()=="Self")
                {
                    String cnic = binding.edittextCNIC.getText().toString();
                    String Fullname = binding.edittextFulname.getText().toString();
                    String relation_name=binding.spinnerRelation.getSelectedItem().toString();
                    String patient_name="";
                    String DOB=binding.edittextDOB.getText().toString();
                    String gender = "Male";
                    if(binding.RadiaButtonFeMale.isChecked())
                        gender = "FeMale";
                    else
                        gender = "Male";
                    RetrofitClient client =
                            RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    Patient p = new Patient();
                    p.CNIC=cnic;
                    p.full_name=Fullname;
                    p.relation=relation_name;
                    p.relative_name=patient_name;
                    p.DOB=DOB;
                    p.Gender=gender;
                    api.Addpatient(p).enqueue(new Callback<String>()
                    {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response)
                        {
                            if(response.isSuccessful())
                            {
                                StaticClass.id=Integer.parseInt(response.body());
                                Toast.makeText(requireContext(),
                                        "Saved  "+ StaticClass.id,
                                        Toast.LENGTH_SHORT).show();
                                //                mainActivity=(MainActivity)getActivity();
                                Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                i.putExtra("staticclass", StaticClass.id);
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
                else
                {
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
                    api.Addpatient(p).enqueue(new Callback<String>()
                    {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response)
                        {
                            if(response.isSuccessful()) {
                                StaticClass.id=Integer.parseInt(response.body());
                                Toast.makeText(requireContext(),
                                        "Saved  "+ StaticClass.id,
                                        Toast.LENGTH_SHORT).show();
                                //                mainActivity=(MainActivity)getActivity();
                                Intent i = new Intent(getActivity(), NextPageofNurseAddNewPatientActivity.class);
                                i.putExtra("staticclass", StaticClass.id);
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
                        public void onFailure(Call<String> call, Throwable t){
                        }
                    });
                }
            }
        });
        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);
        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mysearch(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                mysearch(query);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void mysearch(String query) {
    }
}