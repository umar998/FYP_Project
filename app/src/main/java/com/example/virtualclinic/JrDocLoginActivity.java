package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.virtualclinic.Models.patientsfullrecord;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityJrDocLoginBinding;

import java.util.ArrayList;

public class JrDocLoginActivity extends AppCompatActivity {
    ActivityJrDocLoginBinding binding;
 //   private PatientCheckAppointment.RecyclerViewClickListener listener;
    //RecyclerView recycle;
    //private CustomAdapter customAdapter;
    Intent i = getIntent();

    //int id=i.getIntExtra("jrdoc_id",0);
    //String full_name=i.getStringExtra("full_name");
    ArrayList<patientsfullrecord> data= new ArrayList<>();
    private  void listingdata(){

        RetrofitClient client =
                RetrofitClient.getInstance();
        Api api = client.getMyApi();
//        Call<patientsfullrecord> call=api.MyNewCases(7);
//        api.MyNewCases(7).enqueue(new Callback<ArrayList<patientsfullrecord>>() {
//            @Override
//            public void onResponse(Call<ArrayList<patientsfullrecord>> call, Response<ArrayList<patientsfullrecord>> response) {
//                if(response.isSuccessful())
//                {
//                    data.clear();
//                    data.addAll(response.body());
//                   // binding.rvPatients.getAdapter().notifyDataSetChanged();
//                    Patients_Detail_Adapter adapter= new Patients_Detail_Adapter(data,this);
//                    Patient_Detail_Adapter_test adapter_test = new Patient_Detail_Adapter_test(data,this, listener);
//
////                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    binding.rvPatients.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    binding.rvPatients.setItemAnimator(new DefaultItemAnimator());
//                    binding.rvPatients.setAdapter(adapter_test);
////                    LinearLayoutManager llm= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
////                    binding.rvPatients.setLayoutManager(llm);
////                    binding.rvPatients.setAdapter(adapter);
//                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<patientsfullrecord>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.getMessage()
//                        ,Toast.LENGTH_LONG).show();
//            }
//        });
//        call.enqueue(new Callback<patientsfullrecord>() {
//            @Override
//            public void onResponse(Call<patientsfullrecord> call, Response<patientsfullrecord> response) {
//                if(response.isSuccessful())
//                {
//                    data.clear();
//                    data.addAll(response.body());
//                    binding.rvPatients.getAdapter().notifyDataSetChanged();
//                    Patients_Detail_Adapter adapter= new Patients_Detail_Adapter(getApplicationContext(),data);
//                    LinearLayoutManager llm= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//                    binding.rvPatients.setLayoutManager(llm);
//                    binding.rvPatients.setAdapter(adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<patientsfullrecord> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure" , Toast.LENGTH_LONG).show();
//
//            }
//        });



        //data.add()
    }
//    private void setOnClickListner()
//    {
//
//        Intent i=new Intent(this,PatientsDetailedActivity.class);
//        i.putExtra("full_name")
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJrDocLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listingdata();

        binding.logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();
//                api.Jrlogout(7).enqueue(new Callback<JuniorDoctorLogin>() {
//                    @Override
//                    public void onResponse(Call<JuniorDoctorLogin> call, Response<JuniorDoctorLogin> response) {
//                        if(response.isSuccessful())
//                        {
//                            Intent i = new Intent(JrDocLoginActivity.this, Login_Screen_Activity.class);
//                            startActivity(i);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JuniorDoctorLogin> call, Throwable t) {
//
//                    }
//                });


            }
        });
        binding.textViewUsername.setText("full_name");
    }
//    private static class CustomAdapter extends ArrayAdapter<patientsfullrecord> {
//        private List<patientsfullrecord> data;
//
//        public CustomAdapter(Context context, List<patientsfullrecord> data) {
//            super(context, 0, data);
//            this.data = data;
//        }
//        public void setData(List<patientsfullrecord> newData) {
//            data.clear();
//            data.addAll(newData);
//            notifyDataSetChanged();
//        }
//    }
}

//    private static class CustomAdapter extends ArrayAdapter<Data> {
//
//        private List<Data> data;
//
//        public CustomAdapter(Context context, List<Data> data) {
//            super(context, 0, data);
//            this.data = data;
//        }
//
//        public void setData(List<Data> newData) {
//            data.clear();
//            data.addAll(newData);
//            notifyDataSetChanged();
//        }



//class Data
//{
//    public int Id { get; set; }
//    public string Name { get; set; }
//    public string Description { get; set; }
//}
//    Java code:
//
//        java
//        Copy code
//public class MainActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private CustomAdapter customAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        listView = findViewById(R.id.list_view);
//        customAdapter = new CustomAdapter(this, new ArrayList<Data>());
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.example.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        DataService service = retrofit.create(DataService.class);
//
//        Call<List<Data>> call = service.getData();
//
//        call.enqueue(new Callback<List<Data>>() {
//            @Override
//            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
//                List<Data> data = response.body();
//
//                customAdapter.setData(data);
//                listView.setAdapter(customAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Data>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private interface DataService
//    {
//        @GET("data")
//        Call<List<Data>> getData();
//    }
//
//    private static class CustomAdapter extends ArrayAdapter<Data> {
//
//        private List<Data> data;
//
//        public CustomAdapter(Context context, List<Data> data) {
//            super(context, 0, data);
//            this.data = data;
//        }
//
//        public void setData(List<Data> newData) {
//            data.clear();
//            data.addAll(newData);
//            notifyDataSetChanged();
//        }