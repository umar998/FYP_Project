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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JrDocLoginTestActivity extends AppCompatActivity {
    ActivityJrDocLoginTestBinding binding;
    private boolean isRequestMade = false;
    private int lastVisitId = -1;
    JsonArray jsonArray;
    int visit_id;
    Handler handler;
    private static final int REQUEST_CODE_ACCEPT_CASE = 1;
    ArrayList<Map<String,ArrayList<patientsfullrecord>>> p;
    static  String Docfull_name;
    static int Docid ;
    Set<Integer> addedCases = new HashSet<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ACCEPT_CASE && resultCode == RESULT_OK && data != null) {
            Docfull_name=data.getStringExtra("Docfull_name");
            Docid = data.getIntExtra("Docid", 0);
            binding.textViewUsername.setText(Docfull_name);

            addedCases.clear();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // Make API call here
                if(!isRequestMade)
                {
                    makeApiCall();
                }
            }
        }, 10000);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        binding=ActivityJrDocLoginTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
         Docid=i.getIntExtra("jrdoc_id",0);
        Docfull_name=i.getStringExtra("full_name");

        SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Docfull_name",Docfull_name);
        editor.putInt("id",Docid);
        editor.apply();


        int Doc_id=i.getIntExtra("Doc_id",0);
        String Doc_full_name=i.getStringExtra("Doc_full_name");
        //yha fullname ki jga doc full name aega
        binding.textViewUsername.setText(Docfull_name);

        //mHandler.postDelayed(mRunnableTask, 1000*5);

       // Toast.makeText(JrDocLoginTestActivity.this,"Doctors id : "+StaticClass.id,Toast.LENGTH_LONG).show();

        binding.logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
                //yha id ki jga  ye StaticClass.id aega
                api.Jrlogout(Docid).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(JrDocLoginTestActivity.this,"Logout",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(JrDocLoginTestActivity.this, Login_Screen_Activity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(JrDocLoginTestActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(JrDocLoginTestActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
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


    }
    private void makeApiCall() {

        Retrofit retrofit= new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        api.AssignPatientToDoctor().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Toast.makeText(JrDocLoginTestActivity.this,"Api Called",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        if(visit_id>lastVisitId && !addedCases.contains(visit_id))
        {
            api.MyNewCases(Docid).enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    if(response.isSuccessful())
                    {

                        String json=response.body().toString();
                        //Toast.makeText(JrDocLoginTestActivity.this,json,Toast.LENGTH_LONG).show();
                        jsonArray=JsonParser.parseString(json).getAsJsonArray();
                        for(JsonElement element : jsonArray)
                        {
                            JsonObject jsonObject=element.getAsJsonObject();
                            // JsonObject pObject = element.getAsJsonObject();
                            JsonObject pObject = jsonObject.getAsJsonObject("p");
                            String name = pObject.get("full_name").getAsString();
                            int patient_id = pObject.get("patient_id").getAsInt();
                            String cnic = pObject.get("cnic").getAsString();
                            String relation = pObject.get("relation").getAsString();
                            String relation_name = pObject.get("relative_name").getAsString();
                            String dob = pObject.get("dob").getAsString();
                            String patientdate = pObject.get("date").getAsString();
                            String patienttime = pObject.get("time").getAsString();
                            String gender = pObject.get("gender").getAsString();

                            JsonObject vObject = jsonObject.getAsJsonObject("v");
                            int vital_id = vObject.get("vitalID").getAsInt();
                            String bp = vObject.get("blood_pressure").getAsString();
                            String sugar = vObject.get("sugar").getAsString();
                            String temp = vObject.get("temperature").getAsString();
                            String symptoms = vObject.get("symptoms").getAsString();
                            String image = vObject.get("image").getAsString();
                            //Toast.makeText(JrDocLoginTestActivity.this,"image:"+image,Toast.LENGTH_LONG).show();
                            //String imagedatastring = vObject.get("image").getAsString();
                            //Toast.makeText(JrDocLoginTestActivity.this,imagedatastring,Toast.LENGTH_LONG).show();
                            // Decode image data from Base64 string
//                            byte[] imageData = Base64.decode(imagedatastring, Base64.DEFAULT);
//                            Bitmap bitmap= BitmapFactory.decodeByteArray(imageData, 0, imageData.length);


                            JsonObject xObject = jsonObject.getAsJsonObject("x");
                            visit_id = xObject.get("visit_id").getAsInt();
                            int jrdoc_id = xObject.get("jrdoc_id").getAsInt();
                            String visitdate = xObject.get("date").getAsString();
                            String visittime = xObject.get("time").getAsString();
                            String AssignedDatetime = xObject.get("AssignedDatetime").getAsString();
                            int nurseID=xObject.get("nurseID").getAsInt();
                            PatientObject patientObject = new PatientObject(patient_id, name, cnic, relation, relation_name, dob, gender, patientdate, patienttime);
                           // Toast.makeText(JrDocLoginTestActivity.this, "NurseID"+nurseID, Toast.LENGTH_LONG).show();
                            // vitals vitals = new vitals(vital_id, bp, sugar, temp, symptoms, imagedatastring);

                            addedCases.add(visit_id);
                            binding.accept.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v) {
                                    RetrofitClient client =
                                            RetrofitClient.getInstance();
                                    Api api = client.getMyApi();
                                    api.AcceptedCase(jrdoc_id, patient_id, visit_id).enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if (response.isSuccessful()) {
                                                //Toast.makeText(JrDocLoginTestActivity.this, "Accepted Case", Toast.LENGTH_LONG).show();
                                            } else
                                                Toast.makeText(JrDocLoginTestActivity.this, "Accepted Case Failed", Toast.LENGTH_LONG).show();
                                        }
                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Toast.makeText(JrDocLoginTestActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    addedCases.remove(visit_id);
                                    api.Appointment(patient_id, jrdoc_id, visit_id,nurseID).enqueue(new Callback<String>() {
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
                                    Intent i = new Intent(JrDocLoginTestActivity.this, PatientsDetailedActivity.class);
//                                        i.putExtra("Doc_full_name", StaticClass.docName);
//                                        i.putExtra("Doc_id", StaticClass.id);
                                    i.putExtra("Docfull_name", Docfull_name);
                                    i.putExtra("Docid",Docid);

                                    i.putExtra("patient_id", patient_id);
                                    i.putExtra("full_name", name);
                                    i.putExtra("cnic", cnic);
                                    i.putExtra("relation", relation);
                                    i.putExtra("relative_name", relation_name);
                                    i.putExtra("dob", dob);
                                    i.putExtra("date", patientdate);
                                    i.putExtra("time", patienttime);
                                    i.putExtra("gender", gender);

                                    i.putExtra("vitalID", vital_id);
                                    i.putExtra("blood_pressure", bp);
                                    i.putExtra("sugar", sugar);
                                    i.putExtra("temperature", temp);
                                    i.putExtra("symptoms", symptoms);
                                     i.putExtra("imageData", image);

                                    i.putExtra("visit_id", visit_id);
                                    i.putExtra("jrdoc_id", jrdoc_id);
                                    i.putExtra("date", visitdate);
                                    i.putExtra("time", visittime);
                                    i.putExtra("AssignedDatetime", AssignedDatetime);
                                    startActivity(i);
                                }
                            });

                            if (relation.equals("Spouse") || relation.equals("Child 1") || relation.equals("Child 2")) {
                                binding.patientName.setText("Name :" + relation_name);
                                binding.patientAge.setText("DOB :" + dob);
                            } else {
                                binding.patientName.setText("Name :" + name);
                                binding.patientAge.setText("DOB :" + dob);
                            }
                        }
                    }
                    else
                        Toast.makeText(JrDocLoginTestActivity.this,"No Patient's Available",Toast.LENGTH_LONG).show();
                    // Update the lastVisitId with the latest received visit ID
                    if (jsonArray!=null && jsonArray.size() > 0) {
                        lastVisitId = jsonArray.get(jsonArray.size() - 1).getAsJsonObject().
                                getAsJsonObject("x").get("visit_id").getAsInt();
                    }
                    // Reset the flag and schedule the next API call
                    isRequestMade = false;
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(JrDocLoginTestActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
        // Set the flag to true indicating that the request has been made
        isRequestMade = true;
    }



}