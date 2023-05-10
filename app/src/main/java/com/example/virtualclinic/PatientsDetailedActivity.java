package com.example.virtualclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityPatientsDetailedBinding;
import com.example.virtualclinic.databinding.DurationPopupBinding;
import com.example.virtualclinic.databinding.MedicnePopupBinding;
import com.example.virtualclinic.databinding.TimingPopupBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientsDetailedActivity extends AppCompatActivity {
    ActivityPatientsDetailedBinding binding;
    private AlertDialog.Builder dialogbuilder;
    private  Dialog medicineDialog,durationDialog,timingDialog;
    private MedicnePopupBinding medicinebinding;
    private DurationPopupBinding durationPopupBinding;
    private TimingPopupBinding timingPopupBinding;
    int id= StaticClass.id;

    String Timing="" , medicine="" , Duration="" ;
    ArrayAdapter<String>  adapter;



    private void showMedicineDialog(){
        medicineDialog=new Dialog(this);
        medicinebinding=MedicnePopupBinding.inflate(getLayoutInflater());
        medicineDialog.setContentView(medicinebinding.getRoot());
        medicineDialog.show();

        medicinebinding.CheckBoxBeflam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxBeflam.isChecked())
                {
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
        medicinebinding.CheckBoxSoftin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxSoftin.isChecked())
                {
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxBeflam.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
        medicinebinding.CheckBoxBrufin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxBrufin.isChecked())
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
        medicinebinding.CheckBoxPanadol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxPanadol.isChecked())
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
        medicinebinding.CheckBoxEmoxel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxEmoxel.isChecked())
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
        medicinebinding.CheckBoxDesprin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(medicinebinding.CheckBoxDesprin.isChecked())
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                }
                else
                {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });

        medicinebinding.ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(medicinebinding.CheckBoxBeflam.isChecked())
                {
                    medicine="Beflam";
                    //selectedMedicines.add(medicine);
                }
                if(medicinebinding.CheckBoxBrufin.isChecked())
                {
                    medicine="Brufin";
                    ///selectedMedicines.add(medicine);
                }

                if(medicinebinding.CheckBoxDesprin.isChecked())
                {
                    medicine="Desprin";
                    //selectedMedicines.add(medicine);
                }
                if(medicinebinding.CheckBoxEmoxel.isChecked())
                {
                    medicine="Emoxel";
                    //selectedMedicines.add(medicine);
                }
                if(medicinebinding.CheckBoxPanadol.isChecked())
                {
                    medicine="Panadol";
                    //selectedMedicines.add(medicine);

                }
                if(medicinebinding.CheckBoxSoftin.isChecked())
                {
                    medicine="Softin";
                    //selectedMedicines.add(medicine);
                }

                //Toast.makeText(PatientsDetailedActivity.this,"Medicine :"+medicine,Toast.LENGTH_LONG).show();
                medicineDialog.dismiss();
            }
        });
        medicinebinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineDialog.dismiss();
            }
        });
    }
    private void showDurationDialog(){
        durationDialog=new Dialog(this);
        durationPopupBinding=durationPopupBinding.inflate(getLayoutInflater());
        durationDialog.setContentView(durationPopupBinding.getRoot());
        durationDialog.show();

        durationPopupBinding.CheckBox3Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(durationPopupBinding.CheckBox3Days.isChecked())
                {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                }
                else
                {
                    durationPopupBinding.CheckBox3Days.setEnabled(true);
                    durationPopupBinding.CheckBox5Days.setEnabled(true);
                    durationPopupBinding.CheckBox10Days.setEnabled(true);
                    durationPopupBinding.CheckBox15Days.setEnabled(true);
                }
            }
        });
        durationPopupBinding.CheckBox5Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(durationPopupBinding.CheckBox5Days.isChecked())
                {
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                }
                else
                {
                    durationPopupBinding.CheckBox3Days.setEnabled(true);
                    durationPopupBinding.CheckBox5Days.setEnabled(true);
                    durationPopupBinding.CheckBox10Days.setEnabled(true);
                    durationPopupBinding.CheckBox15Days.setEnabled(true);
                }
            }
        });
        durationPopupBinding.CheckBox10Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(durationPopupBinding.CheckBox10Days.isChecked())
                {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                }
                else
                {
                    durationPopupBinding.CheckBox3Days.setEnabled(true);
                    durationPopupBinding.CheckBox5Days.setEnabled(true);
                    durationPopupBinding.CheckBox10Days.setEnabled(true);
                    durationPopupBinding.CheckBox15Days.setEnabled(true);
                }
            }
        });
        durationPopupBinding.CheckBox15Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(durationPopupBinding.CheckBox15Days.isChecked())
                {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                }
                else
                {
                    durationPopupBinding.CheckBox3Days.setEnabled(true);
                    durationPopupBinding.CheckBox5Days.setEnabled(true);
                    durationPopupBinding.CheckBox10Days.setEnabled(true);
                    durationPopupBinding.CheckBox15Days.setEnabled(true);
                }
            }
        });

        durationPopupBinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(durationPopupBinding.CheckBox3Days.isChecked())
                    Duration="3 Days";
                if(durationPopupBinding.CheckBox5Days.isChecked())
                    Duration="5 Days";
                if(durationPopupBinding.CheckBox10Days.isChecked())
                    Duration="10 Days";
                if(durationPopupBinding.CheckBox15Days.isChecked())
                    Duration="15 Days";
                //Toast.makeText(PatientsDetailedActivity.this,"Duration :"+Duration,Toast.LENGTH_LONG).show();
                durationDialog.dismiss();
            }
        });


        durationPopupBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineDialog.dismiss();
            }
        });

    }
    private void showTimingDialog(){
        timingDialog=new Dialog(this);
        timingPopupBinding=timingPopupBinding.inflate(getLayoutInflater());
        timingDialog.setContentView(timingPopupBinding.getRoot());
        timingDialog.show();
        timingPopupBinding.CheckBoxAllOfThem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(timingPopupBinding.CheckBoxAllOfThem.isChecked())
                {
                    timingPopupBinding.CheckBoxEvening.setEnabled(false);
                    timingPopupBinding.CheckBoxAfternoon.setEnabled(false);
                    timingPopupBinding.CheckBoxMorning.setEnabled(false);
                }
                else
                {
                    timingPopupBinding.CheckBoxEvening.setEnabled(true);
                    timingPopupBinding.CheckBoxAllOfThem.setEnabled(true);
                    timingPopupBinding.CheckBoxAfternoon.setEnabled(true);
                    timingPopupBinding.CheckBoxMorning.setEnabled(true);
                }
            }
        });
        timingPopupBinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(timingPopupBinding.CheckBoxAllOfThem.isChecked())
                {
                    Timing="Morning, Afternoon and Evening";
                }
                if(timingPopupBinding.CheckBoxEvening.isChecked())
                {
                    Timing="Evening";
                }
                if(timingPopupBinding.CheckBoxMorning.isChecked())
                {
                    Timing="Morning";
                }
                if(timingPopupBinding.CheckBoxAfternoon.isChecked())
                {
                    Timing="Afternoon";
                }
               // Toast.makeText(PatientsDetailedActivity.this,"Duration :"+Timing,Toast.LENGTH_LONG).show();
                timingDialog.dismiss();
            }
        });
        timingPopupBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timingDialog.dismiss();
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= ActivityPatientsDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<>());
        binding.listviewpatientmedicnes.setAdapter(adapter);
        binding.medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMedicineDialog();
                if(medicinebinding.CheckBoxSoftin.isChecked())
                {
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxBeflam.setEnabled(false);

                }
            }
        });
        binding.duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDurationDialog();
            }
        });
        binding.timings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimingDialog();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=Timing;
                String med=medicine;
                String duraton=Duration;

                String prescription="Timing: " + time + ", Medicine: " + med + ", Duration: " + duraton;// Check if the medicine already exists in the adapter
                boolean isAlreadyAdded = false;
                for (int i = 0; i < adapter.getCount(); i++) {
                    String item=adapter.getItem(i);
                    if(item.contains(med)){
                        isAlreadyAdded=true;
                        break;
                    }
                }
                if(isAlreadyAdded)
                    Toast.makeText(PatientsDetailedActivity.this,"This Medicine is already added",Toast.LENGTH_LONG).show();
                else {
                    adapter.add(prescription);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Intent i = getIntent();

//        int Doc_id=i.getIntExtra("Doc_id",0);
//        String Doc_full_name=i.getStringExtra("Doc_full_name");
        int Docid=i.getIntExtra("Docid",0);
        String Docfull_name=i.getStringExtra("Docfull_name");

        Toast.makeText(PatientsDetailedActivity.this,"Doc Name"+ Docfull_name,Toast.LENGTH_LONG).show();

        int patient_id=i.getIntExtra("patient_id",0);
        String full_name=i.getStringExtra("full_name");
        String cnic=i.getStringExtra("cnic");
        String relation=i.getStringExtra("relation");
        String relative_name=i.getStringExtra("relative_name");
        String patientdob=i.getStringExtra("dob");
        String patientdate=i.getStringExtra("date");
        String patienttime=i.getStringExtra("time");
        String gender=i.getStringExtra("gender");

        int vital_id=i.getIntExtra("vitalID",0);
        String bp=i.getStringExtra("blood_pressure");
        String sugar=i.getStringExtra("sugar");
        String temperature=i.getStringExtra("temperature");
        String symptoms=i.getStringExtra("symptoms");
        //byte[] imageData = i.getByteArrayExtra("imageData");
        Bitmap imageData=getIntent().getParcelableExtra("imageData");
        //Bitmap bitmap=BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

        int visit_id=i.getIntExtra("visit_id",0);
        int jrdoc_id=i.getIntExtra("jrdoc_id",0);
        String visittime=i.getStringExtra("time");
        String visitdate=i.getStringExtra("date");
        String AssignedDatetime=i.getStringExtra("AssignedDatetime");

       // Bitmap bitmap= BitmapFactory.decodeByteArray(imageData,0,imageData.length);
        if(relative_name=="Spouse")
            binding.textviewPatientname.setText(relative_name);
        else if(relative_name=="Child 1")
            binding.textviewPatientname.setText(relative_name);
        else if(relative_name=="Child 2")
            binding.textviewPatientname.setText(relative_name);

        binding.textviewPatientname.setText(full_name);
        binding.textviewPatientDOB.setText(patientdob);
        binding.textviewGender.setText(gender);
        binding.textviewBP.setText(bp);
        binding.textviewSugar.setText(sugar);
        binding.textviewTemp.setText(temperature);
        binding.textviewSymptoms.setText(symptoms);
        binding.imagesss.setImageBitmap(imageData);

        RetrofitClient client =
                RetrofitClient.getInstance();
        Api api = client.getMyApi();
        api.Gettingappointmentid(patient_id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    id=Integer.parseInt(response.body());
                    Toast.makeText(PatientsDetailedActivity.this,
                            "Appointment ID: "+id,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                List<Prescription> prescriptions=new ArrayList<>();
                for(int i=0;i<adapter.getCount();i++)
                {
                    //adapter.getItem(0).split(",")[2].split(" ");
                    String timing=adapter.getItem(i).split(",")[0].split(":")[1].trim();
                    String duration=""+adapter.getItem(i).split(",")[2].split(" ")[2]+" Days" ;
                    String med=adapter.getItem(i).split(",")[1].split(":")[1].trim();
                    int idd=id;
                    Prescription prescription=new Prescription(idd,med,duration,timing);
                    prescriptions.add(prescription);
                }
                RetrofitClient client =
                        RetrofitClient.getInstance();
                Api api = client.getMyApi();


                Call<String> call=api.Addingprescription(prescriptions);
                call.enqueue(new Callback<String>()
                {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(PatientsDetailedActivity.this,"Patients Prescription Send",Toast.LENGTH_LONG).show();
                            Intent ii = new Intent(PatientsDetailedActivity.this,JrDocLoginTestActivity.class);
                            ii.putExtra("Docfull_name",Docfull_name);
                            ii.putExtra("Docid",Docid);
                            setResult(RESULT_OK,ii);
                            finish();
                            Toast.makeText(PatientsDetailedActivity.this,"Patients id you want to send "+patient_id,Toast.LENGTH_LONG).show();

//                            ReportsFragment reportsFragment= new ReportsFragment();
//                            Bundle i = new Bundle();
//                            //Intent i=new Intent(PatientsDetailedActivity.this,ReportsFragment.class);
//                            i.putInt("patient_id",patient_id);
//                            i.putString("full_name",full_name);
//                            i.putString("relative_name",relative_name);
//                            i.putString("patientdob",patientdob);
//                            i.putString("bp",bp);
//                            i.putString("sugar",sugar);
//                            i.putString("temperature",temperature);
//                            i.putString("symptoms",symptoms);
//                            i.putParcelableArrayList("prescriptions", (ArrayList<? extends Parcelable>) prescriptions);
//                            reportsFragment.setArguments(i);
//                            getSupportFragmentManager().beginTransaction().add(android.R.id.content,reportsFragment).commit();
//                            startActivityForResult(i,0);
                            //startActivity(ii);
                        }else
                            Toast.makeText(PatientsDetailedActivity.this,"Patients Prescription not Send"+response.code(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(PatientsDetailedActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                api.Updatingvitalstatus(vital_id).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful())
                            Toast.makeText(PatientsDetailedActivity.this,"vitals updated",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });
        Toast.makeText(PatientsDetailedActivity.this,"This is : "+patient_id,Toast.LENGTH_LONG).show();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu);
//    }


}