package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Adapter.Patients_Detail_Adapter;
import com.example.virtualclinic.Models.AppointmentDataNew;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.SrDocAppointments;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivitySrDocLoginBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SrDocLoginActivity extends AppCompatActivity {
    ActivitySrDocLoginBinding binding;
    private RecyclerView recyclerView;
    private static final int REQUEST_DETAILS = 1;
    private Context mContext;
    private Patients_Detail_Adapter adapter;
    private ArrayList<AppointmentDataNew> appointmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySrDocLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        Intent i = getIntent();
        int SrDoc_id = i.getIntExtra("Srdoc_id", 0);
        String SrDoc_full_name = i.getStringExtra("full_name");
        int Doc_id = i.getIntExtra("Doc_id", 0);
        String Doc_full_name = i.getStringExtra("Doc_full_name");
        binding.textViewUsername.setText(Doc_full_name);

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
                api.Srlogout(StaticClass.id).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SrDocLoginActivity.this, "Logout", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SrDocLoginActivity.this, Login_Screen_Activity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(SrDocLoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(SrDocLoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        binding.textViewUsername.setText(StaticClass.docName);
        recyclerView = findViewById(R.id.rv_appointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(SrDocLoginActivity.this));
        adapter = new Patients_Detail_Adapter(new AppointmentClick() {
            @Override
            public void onAppointmentClicked(SrDocAppointments appointments) {
                getPatientDetails(appointments);
            }
        });
        recyclerView.setAdapter(adapter);
//        adapter.setData(getAppointments());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetRetrofitInstance.getApiService().MyNewAppointments(SrDoc_id).enqueue(new Callback<ArrayList<SrDocAppointments>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SrDocAppointments>> call, Response<ArrayList<SrDocAppointments>> response) {
                        adapter.setData(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SrDocAppointments>> call, Throwable t) {

                    }
                });
            }
        },5000);

      /*  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())

                        .build();
                Api api = retrofit.create(Api.class);

                api.MyNewAppointments(SrDoc_id).enqueue(new Callback<ArrayList<SrDocAppointments>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SrDocAppointments>> call, Response<ArrayList<SrDocAppointments>> response) {
                        if (response.isSuccessful()) {

                        *//*    List<SrDocAppointments> appointments=new ArrayList<>();
                            String json = response.body().toString();
                            //Toast.makeText(SrDocLoginActivity.this, json, Toast.LENGTH_LONG).show();
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
//                            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
//                            JsonObject pObject = jsonObject.getAsJsonObject("s");
                            for(JsonElement element : jsonArray){
                                JsonObject pObject = element.getAsJsonObject();
                                int appointment_id = pObject.get("appointment_id").getAsInt();
                                int patient_id = pObject.get("patient_id").getAsInt();
                                int jrdoc_id = pObject.get("jrdoc_id").getAsInt();
                                //float rating = pObject.get("rating").getAsFloat();
                                String date = pObject.get("date").getAsString();
                                String time = pObject.get("time").getAsString();
                                int status = pObject.get("status").getAsInt();
                                int srdoc_id = pObject.get("srdoc_id").getAsInt();
                                int visit_id = pObject.get("visit_id").getAsInt();
                                SrDocAppointments srDocAppointments = new SrDocAppointments(appointment_id, patient_id, jrdoc_id, date, time, status, srdoc_id, visit_id);
                               // Toast.makeText(SrDocLoginActivity.this, "visit"+visit_id, Toast.LENGTH_LONG).show();
                                appointments.add(srDocAppointments);
                            }*//*
                            adapter.setData(response.body());
                        } else {
                            Toast.makeText(SrDocLoginActivity.this, "no cases", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SrDocAppointments>> call, Throwable t) {
                    }
                });
            }
        }, 5000);*/
    }

    private void getPatientDetails(SrDocAppointments appointments) {


        GetRetrofitInstance.getApiService().Getpresdetails(appointments.getAppointment_id()).enqueue(new Callback<List<Prescription>>() {
            @Override
            public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {

                getAppointsDetails(appointments,response.body());
            }

            @Override
            public void onFailure(Call<List<Prescription>> call, Throwable t) {

            }
        });


    }

    private void getAppointsDetails(SrDocAppointments appointments,List<Prescription> list) {
        GetRetrofitInstance.getApiService().AppointmentDetails(appointments.getVisit_id()).enqueue(new Callback<ArrayList<AppointmentDataNew>>() {
            @Override
            public void onResponse(Call<ArrayList<AppointmentDataNew>> call, Response<ArrayList<AppointmentDataNew>> response) {
                appointmentsList=response.body();
                if(appointmentsList!=null && appointmentsList.size()>0)
                {
                    AppointmentDataNew appointmentData = appointmentsList.get(0);
                    Intent intent = new Intent(SrDocLoginActivity.this, SRDocAssignCasesDetailsActivty.class);
                    intent.putExtra("appointmentData",appointmentData);
                    intent.putParcelableArrayListExtra("listOfPres", (ArrayList<? extends Parcelable>) list);
                    //intent.putExtra("listOfPres",new JSONArray(list).toString());
                    startActivity(intent);

                }
                else {
                    Toast.makeText(SrDocLoginActivity.this, "No appointment data available", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<AppointmentDataNew>> call, Throwable t) {

            }
        });
    }


    private ArrayList<SrDocAppointments> getAppointments() {
        String data = "[\n" +
                "   {\n" +
                "      \"appointment_id\":1066,\n" +
                "      \"patient_id\":2061,\n" +
                "      \"jrdoc_id\":7,\n" +
                "      \"rating\":null,\n" +
                "      \"date\":\"09/05/2023\",\n" +
                "      \"time\":\"10:10 pm\",\n" +
                "      \"status\":0,\n" +
                "      \"srdoc_id\":1,\n" +
                "      \"visit_id\":1089\n" +
                "   },\n" +
                "   {\n" +
                "      \"appointment_id\":1067,\n" +
                "      \"patient_id\":2061,\n" +
                "      \"jrdoc_id\":7,\n" +
                "      \"rating\":null,\n" +
                "      \"date\":\"09/05/2023\",\n" +
                "      \"time\":\"10:47 pm\",\n" +
                "      \"status\":0,\n" +
                "      \"srdoc_id\":1,\n" +
                "      \"visit_id\":1089\n" +
                "   },\n" +
                "   {\n" +
                "      \"appointment_id\":1068,\n" +
                "      \"patient_id\":2062,\n" +
                "      \"jrdoc_id\":7,\n" +
                "      \"rating\":null,\n" +
                "      \"date\":\"09/05/2023\",\n" +
                "      \"time\":\"10:49 pm\",\n" +
                "      \"status\":0,\n" +
                "      \"srdoc_id\":1,\n" +
                "      \"visit_id\":1090\n" +
                "   }\n" +
                "]";
        Gson gson = new Gson();

        return gson.fromJson(data, new TypeToken<ArrayList<SrDocAppointments>>() {
        }.getType());

    }





  public interface AppointmentClick{
        public void onAppointmentClicked(SrDocAppointments appointments);
    }
}