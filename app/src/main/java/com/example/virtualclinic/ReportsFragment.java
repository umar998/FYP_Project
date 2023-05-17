package com.example.virtualclinic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.virtualclinic.Adapter.Patients_Reports_Adapter;
import com.example.virtualclinic.Models.GettingReports;
import com.example.virtualclinic.Models.PatientPrescriptionDetail;
import com.example.virtualclinic.Models.PatientsReport;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.PrescriptionAndAppointmnet;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.databinding.FragmentReportsBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportsFragment extends Fragment {
    FragmentReportsBinding binding;
    JsonArray jsonArray;
    int nurseid= StaticClass.id;
    private List<PatientPrescriptionDetail> reportsList;
    private Patients_Reports_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        binding = FragmentReportsBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment

        binding.rvReports.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new Patients_Reports_Adapter(new AppointmentClick() {
            @Override
            public void onAppointmentClicked(GettingReports report) {
                getPatientDetails(report);
            }
        });
        binding.rvReports.setAdapter(adapter);
        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentAppoints(nurseid);
                binding.swipeContainer.setRefreshing(false);
            }
        });

        return binding.getRoot();
    }


    private void getCurrentAppoints(int nurse_id){
        GetRetrofitInstance.getApiService().Gettingappointments(nurse_id).enqueue(new Callback<ArrayList<GettingReports>>() {
            @Override
            public void onResponse(Call<ArrayList<GettingReports>> call, Response<ArrayList<GettingReports>> response) {
                if (response.body().size() != 0) {

                    adapter.setData(response.body());
                    Toast.makeText(requireContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setData(new ArrayList<>());
                    Toast.makeText(requireContext(), "No Appointments Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GettingReports>> call, Throwable t) {

            }
        });
    }
    private void getPatientDetails(GettingReports reports)
    {
        GetRetrofitInstance.getApiService().GettingDoneaptdetails(reports.getSrDocAppointments().getAppointment_id()).enqueue(new Callback<List<PatientPrescriptionDetail>>() {
            @Override
            public void onResponse(Call<List<PatientPrescriptionDetail>> call, Response<List<PatientPrescriptionDetail>> response) {
                reportsList= response.body();
                if (reportsList != null && reportsList.size() > 0) {
                    PatientPrescriptionDetail reportsdata= reportsList.get(0);
                    Toast.makeText(requireContext(),"Response "+reportsdata,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(requireContext(), PatientsReportNextActivity.class);
                    intent.putExtra("listOfPres", reportsdata);
                    //intent.putParcelableArrayListExtra("listOfPres", (ArrayList<? extends Parcelable>) list);
                    //intent.putExtra("listOfPres",new JSONArray(list).toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(requireContext(), "No appointment data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PatientPrescriptionDetail>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getCurrentAppoints(nurseid);
            }
        }, 5000);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    public interface AppointmentClick {
        public void onAppointmentClicked(GettingReports report);

    }
}