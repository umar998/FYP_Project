package com.example.virtualclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualclinic.Models.AppointmentDataNew;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.databinding.ActivitySrdocAssignCasesDetailsActivtyBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SRDocAssignCasesDetailsActivty extends AppCompatActivity {
    ActivitySrdocAssignCasesDetailsActivtyBinding binding;
    float ratevalue;
    float temp;
    static String Doc_full_name;
    static int Doc_id;
    AppointmentDataNew appointmentDataNew;
    List<Prescription> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySrdocAssignCasesDetailsActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {

            appointmentDataNew = (AppointmentDataNew) getIntent().getSerializableExtra("appointmentData");
            list= getIntent().getParcelableArrayListExtra("listOfPres");
            Doc_full_name=getIntent().getStringExtra("Doc_full_name");
            Doc_id=getIntent().getIntExtra("Doc_id",0);


        }

        if(appointmentDataNew.getPatientObject().getRelation().equals("Spouse"))
            binding.textviewPatientname.setText(appointmentDataNew.getPatientObject().getRelative_name());
        else if(appointmentDataNew.getPatientObject().getRelation().equals("Child 1"))
            binding.textviewPatientname.setText(appointmentDataNew.getPatientObject().getRelative_name());
        else if(appointmentDataNew.getPatientObject().getRelation().equals("Child 1"))
            binding.textviewPatientname.setText(appointmentDataNew.getPatientObject().getRelative_name());
        else if(appointmentDataNew.getPatientObject().getRelation().equals("Self"))
            binding.textviewPatientname.setText(appointmentDataNew.getPatientObject().getFull_name());
        int patientID=appointmentDataNew.getPatientObject().getPatient_id();
        int appointmentID=appointmentDataNew.getSrDocAppointments().getAppointment_id();
        binding.textviewPatientDOB.setText(appointmentDataNew.getPatientObject().getDob());
        binding.textviewGender.setText(appointmentDataNew.getPatientObject().getGender());
        binding.textviewBP.setText(appointmentDataNew.getVitals().getBp());
        binding.textviewSugar.setText(appointmentDataNew.getVitals().getSugar());
        binding.textviewTemp.setText(appointmentDataNew.getVitals().getTemp());
        binding.textviewSymptoms.setText(appointmentDataNew.getVitals().getSymptoms());
        binding.textviewTime.setText(appointmentDataNew.getAcceptCase().getTime());

        String prescriptionText = "";
        if (list != null) {
            for (Prescription prescription : list) {
                prescriptionText += "Medicine: " + prescription.getMedicine() + "  " +
                        "Duration: " + prescription.getDuration() + "  " +
                        "Timing: " + prescription.getTiming() + "\n";
            }
            binding.textviewPrescription.setText(prescriptionText);
        } else {
            // Handle the case when prescriptions is null
            // You can display an appropriate message or take alternative actions
        }
        binding.ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratevalue = binding.ratingbar.getRating();
                if (ratevalue <= 1 && ratevalue > 0)
                    binding.ratecount.setText(ratevalue + "");
                else if (ratevalue <= 2 && ratevalue > 1)
                    binding.ratecount.setText(ratevalue + "");
                else if (ratevalue <= 3 && ratevalue > 2)
                    binding.ratecount.setText(ratevalue + "");
                else if (ratevalue <= 4 && ratevalue > 3)
                    binding.ratecount.setText(ratevalue + "");
                else if (ratevalue <= 5 && ratevalue > 4)
                    binding.ratecount.setText(ratevalue + "");
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Float.parseFloat(binding.ratecount.getText().toString());
                Toast.makeText(SRDocAssignCasesDetailsActivty.this, "Rating :" + temp, Toast.LENGTH_LONG).show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);
                //Toast.makeText(SRDocAssignCasesDetailsActivty.this,"Rating :"+rating,Toast.LENGTH_LONG).show();
                api.DoneAppointment(appointmentID, temp, patientID).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SRDocAssignCasesDetailsActivty.this, "Rating's Done", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SRDocAssignCasesDetailsActivty.this, SrDocLoginActivity.class);
                            i.putExtra("Doc_full_name", Doc_full_name);
                            i.putExtra("Doc_id", Doc_id);
                            // i.putParcelableArrayListExtra("updatedAppointments", (ArrayList<? extends Parcelable>) updatedAppointments);
                            setResult(RESULT_OK, i);
                            finish();
                            //startActivity(i);
                        } else {
                            Toast.makeText(SRDocAssignCasesDetailsActivty.this, "Rating's Error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(SRDocAssignCasesDetailsActivty.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                binding.ratingbar.setRating(0);
                binding.ratecount.setText("");
            }
        });


//        Intent i = getIntent();
//        String Doc_full_name = i.getStringExtra("Doc_full_name");
//        int Doc_id = i.getIntExtra("Doc_id", 0);
//
//        int visit_id = i.getIntExtra("visit_id", 0);
//        String visitdate = i.getStringExtra("visitdate");
//        String visittime = i.getStringExtra("visittime");
//        String AssignedDatetime = i.getStringExtra("AssignedDatetime");
//
//        int patient_id = i.getIntExtra("patient_id", 0);
//        String patientname = i.getStringExtra("patientname");
//        String relation = i.getStringExtra("relation");
//        //Toast.makeText(SRDocAssignCasesDetailsActivty.this,"patient :"+patientname,Toast.LENGTH_LONG).show();
//        String relation_name = i.getStringExtra("relation_name");
//        String patientdob = i.getStringExtra("patientdob");
//        String patientgender = i.getStringExtra("patientgender");
//        String bp = i.getStringExtra("bp");
//        String sugar = i.getStringExtra("sugar");
//        String temperature = i.getStringExtra("temperature");
//        String symptoms = i.getStringExtra("symptoms");
//
//        int jrdoc_id = i.getIntExtra("jrdoc_id", 0);
//        String Jrfull_name = i.getStringExtra("full_name");
//        ArrayList<Prescription> prescriptions = getIntent().getParcelableArrayListExtra("prescriptions");
//
//        int acceptCaseID = i.getIntExtra("acceptCaseID", 0);
//        String acceptcasetime = i.getStringExtra("acceptcasetime");
//
//        int appointment_id = i.getIntExtra("appointment_id", 0);
//        String Srdocdate = i.getStringExtra("Srdocdate");
//        String Srdoctime = i.getStringExtra("Srdoctime");
//        int srdoc_id = i.getIntExtra("srdoc_id", 0);
//
//        if (relation.equals("Spouse"))
//            binding.textviewPatientname.setText(relation_name);
//        else if (relation.equals("Child 1"))
//            binding.textviewPatientname.setText(relation_name);
//        else if (relation.equals("Child 2"))
//            binding.textviewPatientname.setText(relation_name);
//        else if (relation.equals("Self"))
//            binding.textviewPatientname.setText(patientname);
//
//        binding.textviewPatientDOB.setText(patientdob);
//        binding.textviewGender.setText(patientgender);
//        binding.textviewBP.setText(bp);
//        binding.textviewSugar.setText(sugar);
//        binding.textviewTemp.setText(temperature);
//        binding.textviewSymptoms.setText(symptoms);
//        binding.textviewTime.setText(acceptcasetime);
//        String prescriptionText = "";
////        for (Prescription prescription : prescriptions) {
////            prescriptionText +="Medicine:  "+prescription.getMedicine() +"  "+"Duration: "+prescription.getDuration()+
////                    "  "+"Timing: "+prescription.getTiming()+"\n";
////        }
////       binding.textviewPrescription.setText(prescriptionText);
//        if (prescriptions != null) {
//            for (Prescription prescription : prescriptions) {
//                prescriptionText += "Medicine: " + prescription.getMedicine() + "  " +
//                        "Duration: " + prescription.getDuration() + "  " +
//                        "Timing: " + prescription.getTiming() + "\n";
//            }
//            binding.textviewPrescription.setText(prescriptionText);
//        } else {
//            // Handle the case when prescriptions is null
//            // You can display an appropriate message or take alternative actions
//        }
//        binding.textviewPrescription.setText(prescriptionText);

//        binding.ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                ratevalue = binding.ratingbar.getRating();
//                if (ratevalue <= 1 && ratevalue > 0)
//                    binding.ratecount.setText(ratevalue + "");
//                else if (ratevalue <= 2 && ratevalue > 1)
//                    binding.ratecount.setText(ratevalue + "");
//                else if (ratevalue <= 3 && ratevalue > 2)
//                    binding.ratecount.setText(ratevalue + "");
//                else if (ratevalue <= 4 && ratevalue > 3)
//                    binding.ratecount.setText(ratevalue + "");
//                else if (ratevalue <= 5 && ratevalue > 4)
//                    binding.ratecount.setText(ratevalue + "");
//            }
//        });
//
    }
}