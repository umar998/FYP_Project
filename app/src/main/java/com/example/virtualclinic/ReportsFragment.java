package com.example.virtualclinic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.virtualclinic.Adapter.Patients_Reports_Adapter;
import com.example.virtualclinic.Models.GettingReports;
import com.example.virtualclinic.Models.PatientsDetailedPrescripedModel;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.databinding.FragmentReportsBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportsFragment extends Fragment {
    FragmentReportsBinding binding;
    JsonArray jsonArray;
    int nurseid= StaticClass.id;
    private List<PatientsDetailedPrescripedModel> reportsList;
    private Patients_Reports_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        binding = FragmentReportsBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment

        binding.rvReports.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new Patients_Reports_Adapter(report -> getPatientDetails(report));
        binding.rvReports.setAdapter(adapter);
//        adapter.setData(getDummyData());
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

    private List<GettingReports> getDummyData() {

     String data= "[\n" +
             "   {\n" +
             "      \"a\":{\n" +
             "         \"appointment_id\":1087,\n" +
             "         \"patient_id\":2084,\n" +
             "         \"jrdoc_id\":7,\n" +
             "         \"rating\":null,\n" +
             "         \"date\":\"17/05/2023\",\n" +
             "         \"time\":\"10:55 am\",\n" +
             "         \"status\":0,\n" +
             "         \"srdoc_id\":null,\n" +
             "         \"visit_id\":1112,\n" +
             "         \"shown\":0,\n" +
             "         \"nurseID\":2\n" +
             "      },\n" +
             "      \"pat\":{\n" +
             "         \"patient_id\":2084,\n" +
             "         \"cnic\":\"1\",\n" +
             "         \"full_name\":\"Umar\",\n" +
             "         \"relation\":\"Self\",\n" +
             "         \"relative_name\":\"\",\n" +
             "         \"dob\":\"1899\",\n" +
             "         \"gender\":\"Male\",\n" +
             "         \"date\":\"17/05/2023\",\n" +
             "         \"time\":\"10:53 am\"\n" +
             "      }\n" +
             "   }\n" +
             "]";

        return  new Gson().fromJson(data, new TypeToken<ArrayList<GettingReports>>() {
        }.getType());
    }

    private void getPatientDetails(GettingReports reports)
    {
        GetRetrofitInstance.getApiService().GettingDoneaptdetails(reports.getSrDocAppointments().getAppointment_id()).enqueue(new Callback<ArrayList<PatientsDetailedPrescripedModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PatientsDetailedPrescripedModel>> call, Response<ArrayList<PatientsDetailedPrescripedModel>> response) {



                  reportsList= response.body();
                if (reportsList != null && reportsList.size() > 0) {
                    Intent intent = new Intent(requireContext(), PatientsReportNextActivity.class);
                    intent.putExtra("listOfPres", new Gson().toJson(reportsList));
                    //intent.putParcelableArrayListExtra("listOfPres", (ArrayList<? extends Parcelable>) list);
                    //intent.putExtra("listOfPres",new JSONArray(list).toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(requireContext(), "No appointment data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PatientsDetailedPrescripedModel>> call, Throwable t) {

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