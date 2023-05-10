package com.example.virtualclinic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.virtualclinic.Adapter.Patients_Detail_Adapter;
import com.example.virtualclinic.Models.PatientsReport;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.databinding.FragmentHomeBinding;
import com.example.virtualclinic.databinding.FragmentReportsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReportsFragment extends Fragment {
    String full_name ,relative_name,patientdob,bp,sugar,temperature,symptoms;
    FragmentReportsBinding binding;
    int patient_id;
    ArrayList<Prescription> prescriptions;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_reports,container,false);
        binding= FragmentReportsBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment




        Bundle i = getArguments();

        //Intent i = getActivity().getIntent();
        if(i!=null)
        {
             patient_id=i.getInt("patient_id",0);
             full_name=i.getString("full_name");
             relative_name=i.getString("relative_name");
             patientdob=i.getString("patientdob");
             bp=i.getString("bp");
             sugar=i.getString("sugar");
             temperature=i.getString("temperature");
             symptoms=i.getString("symptoms");
             prescriptions=i.getParcelableArrayList("prescriptions");
        }


        //Patients_Detail_Adapter adapter= new Patients_Detail_Adapter(ReportsFragment.this,fullNames);
        //List<PatientsReport> patients=getActivity().getIntent()

        ArrayAdapter<String> adapter=new ArrayAdapter<>(requireContext(),R.layout.fragment_reports,R.id.full_name_textview,
                Arrays.asList(full_name));
        binding.ListVewPatientReport.setAdapter(adapter);

        binding.ListVewPatientReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(requireContext(),PatientsReportNextActivity.class);
                i.putExtra("patient_id", patient_id);
                i.putExtra("full_name", full_name);
                i.putExtra("relative_name", relative_name);
                i.putExtra("patientdob", patientdob);
                i.putExtra("bp", bp);
                i.putExtra("sugar", sugar);
                i.putExtra("temperature", temperature);
                i.putExtra("symptoms", symptoms);
                i.putParcelableArrayListExtra("prescriptions", (ArrayList<? extends Parcelable>) prescriptions);

            }
        });


        return binding.getRoot();



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}