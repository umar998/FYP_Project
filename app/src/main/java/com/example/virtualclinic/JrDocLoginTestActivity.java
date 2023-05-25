package com.example.virtualclinic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.PatientObject;
import com.example.virtualclinic.Models.patientsfullrecord;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityJrDocLoginTestBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JrDocLoginTestActivity extends AppCompatActivity {
    ActivityJrDocLoginTestBinding binding;
    JsonArray jsonArray;
    int visit_id;
    Handler handler;
    private static final int REQUEST_CODE_ACCEPT_CASE = 1;
    ArrayList<Map<String, ArrayList<patientsfullrecord>>> p;
    static String Docfull_name;
    static int Docid;


    int patientID = -1, visitID = -1, jrDocId = -1, nurseId = -1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ACCEPT_CASE && resultCode == RESULT_OK && data != null) {
            Docfull_name = data.getStringExtra("Docfull_name");
            Docid = data.getIntExtra("Docid", 0);
            binding.textViewUsername.setText(Docfull_name);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.textViewUsername.setText(Docfull_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityJrDocLoginTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        Docid = i.getIntExtra("jrdoc_id", 0);
        Docfull_name = i.getStringExtra("full_name");

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Docfull_name", Docfull_name);
        editor.putInt("id", Docid);
        editor.apply();

        int Doc_id = i.getIntExtra("Doc_id", 0);
        String Doc_full_name = i.getStringExtra("Doc_full_name");
        //yha fullname ki jga doc full name aega
        binding.textViewUsername.setText(Docfull_name);
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
                //yha id ki jga  ye StaticClass.id aega
                api.Jrlogout(Docid).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(JrDocLoginTestActivity.this, "Logout", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(JrDocLoginTestActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(JrDocLoginTestActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeApiCall();
                binding.swipeContainer.setRefreshing(false);
            }
        });
        makeApiCall();
        binding.accept.setOnClickListener(view -> {
            if (patientID != -1 && visitID != -1) {
                GetRetrofitInstance.getApiService().AcceptedCase(jrDocId, patientID, visitID).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                        } else
                            Toast.makeText(JrDocLoginTestActivity.this, "Accepted Case Failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(JrDocLoginTestActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                RetrofitClient.getInstance().getMyApi().Appointment(patientID, jrDocId, visitID, nurseId).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            //Toast.makeText(JrDocLoginTestActivity.this, "Appointment", Toast.LENGTH_LONG).show();


                        } else
                            Toast.makeText(JrDocLoginTestActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


                try {
                    Intent i1 = new Intent(JrDocLoginTestActivity.this, PatientsDetailedActivity.class);
                    i1.putExtra("newCaseResponse", rootNewCaseResponse);
                    binding.patientName.setText("");
                    binding.patientAge.setText("");
                    startActivity(i1);
                } catch (Exception e) {

                }

            }
        });
    }

    String rootNewCaseResponse = "";

    private void makeApiCall() {
        GetRetrofitInstance.getApiService().AssignPatientToDoctor().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    GetRetrofitInstance.getApiService().MyNewCases(Docid).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                parseNewCaseRespnse(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(JrDocLoginTestActivity.this,
                        "AssignPatientToDoctor Not working", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void parseNewCaseRespnse(Response<ResponseBody> response) {
        try {
            String jsonResponse = response.body().string();
            rootNewCaseResponse = jsonResponse;

            JSONArray rootObj = new JSONArray(jsonResponse);
            JSONObject firstItem = rootObj.getJSONObject(0);

            // getting patient Details From P Obj
            JSONObject pObj = firstItem.getJSONObject("p");
            patientID = pObj.getInt("patient_id");
            String patientCNIC = pObj.getString("cnic");
            String patientFullName = pObj.getString("full_name");
            String patientRelation = pObj.getString("relation");
            String patientRelativeName = pObj.getString("relative_name");
            String patientDob = pObj.getString("dob");
            String patientGender = pObj.getString("gender");
            String patientDate = pObj.getString("date");
            String patientTime = pObj.getString("time");

            // getting vitals form V Obj
            JSONObject vitalObj = firstItem.getJSONObject("v");
            int vitalId = vitalObj.getInt("vitalID");
            String vitalBP = vitalObj.getString("blood_pressure");
            String vitalSugar = vitalObj.getString("sugar");
            String vitalTemp = vitalObj.getString("temperature");
            String vitalSymptoms = vitalObj.getString("symptoms");
            if (vitalObj.has("image")) {
                String vitalSImage = vitalObj.getString("image");
            }

            // getting visits form X Obj
            JSONObject visitsObj = firstItem.getJSONObject("x");
            visitID = visitsObj.getInt("visit_id");
            jrDocId = visitsObj.getInt("jrdoc_id");
            int status = visitsObj.getInt("status");
            nurseId = visitsObj.getInt("nurseID");
            String visitDate = visitsObj.getString("date");
            String visitTime = visitsObj.getString("time");
            String visitAssignedDatetime = visitsObj.getString("AssignedDatetime");


            if (patientRelation.equals("Spouse") || patientRelation.equals("Child 1") || patientRelation.equals("Child 2")) {
                binding.patientName.setText("Name :" + patientRelativeName);
                binding.patientAge.setText("DOB :" + patientDob);
            } else {
                binding.patientName.setText("Name :" + patientFullName);
                binding.patientAge.setText("DOB :" + patientDob);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}