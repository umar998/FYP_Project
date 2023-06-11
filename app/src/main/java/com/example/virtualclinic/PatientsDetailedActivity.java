package com.example.virtualclinic;
import static com.example.virtualclinic.JrDocLoginTestActivity.Docfull_name;
import static com.example.virtualclinic.JrDocLoginTestActivity.Docid;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.databinding.ActivityPatientsDetailedBinding;
import com.example.virtualclinic.databinding.DurationPopupBinding;
import com.example.virtualclinic.databinding.MedicnePopupBinding;
import com.example.virtualclinic.databinding.TimingPopupBinding;
import com.example.virtualclinic.rest.GetRetrofitInstance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientsDetailedActivity extends AppCompatActivity {
    ActivityPatientsDetailedBinding binding;
    private Dialog medicineDialog, durationDialog, timingDialog;
    private MedicnePopupBinding medicinebinding;
    private DurationPopupBinding durationPopupBinding;
    private TimingPopupBinding timingPopupBinding;
    int id = StaticClass.id, visitId = -1 , vitalsId = -1 , patient_id=-1,jrdoc_id=-1;

    String Timing = "", medicine = "", Duration = "";
    ArrayAdapter<String> adapter;


    String newCaseResponse = "";

    private void showMedicineDialog() {
        medicineDialog = new Dialog(this);
        medicinebinding = MedicnePopupBinding.inflate(getLayoutInflater());
        medicineDialog.setContentView(medicinebinding.getRoot());
        medicineDialog.show();

        medicinebinding.CheckBoxBeflam.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (medicinebinding.CheckBoxBeflam.isChecked()) {
                medicinebinding.CheckBoxBrufin.setEnabled(false);
                medicinebinding.CheckBoxDesprin.setEnabled(false);
                medicinebinding.CheckBoxEmoxel.setEnabled(false);
                medicinebinding.CheckBoxPanadol.setEnabled(false);
                medicinebinding.CheckBoxSoftin.setEnabled(false);

            } else {
                medicinebinding.CheckBoxBeflam.setEnabled(true);
                medicinebinding.CheckBoxBrufin.setEnabled(true);
                medicinebinding.CheckBoxDesprin.setEnabled(true);
                medicinebinding.CheckBoxEmoxel.setEnabled(true);
                medicinebinding.CheckBoxPanadol.setEnabled(true);
                medicinebinding.CheckBoxSoftin.setEnabled(true);
            }
        });
        medicinebinding.CheckBoxSoftin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (medicinebinding.CheckBoxSoftin.isChecked()) {
                medicinebinding.CheckBoxBrufin.setEnabled(false);
                medicinebinding.CheckBoxDesprin.setEnabled(false);
                medicinebinding.CheckBoxEmoxel.setEnabled(false);
                medicinebinding.CheckBoxPanadol.setEnabled(false);
                medicinebinding.CheckBoxBeflam.setEnabled(false);

            } else {
                medicinebinding.CheckBoxBeflam.setEnabled(true);
                medicinebinding.CheckBoxBrufin.setEnabled(true);
                medicinebinding.CheckBoxDesprin.setEnabled(true);
                medicinebinding.CheckBoxEmoxel.setEnabled(true);
                medicinebinding.CheckBoxPanadol.setEnabled(true);
                medicinebinding.CheckBoxSoftin.setEnabled(true);
            }
        });
        medicinebinding.CheckBoxBrufin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (medicinebinding.CheckBoxBrufin.isChecked()) {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                } else {
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
                if (medicinebinding.CheckBoxPanadol.isChecked()) {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                } else {
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
                if (medicinebinding.CheckBoxEmoxel.isChecked()) {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxDesprin.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                } else {
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
                if (medicinebinding.CheckBoxDesprin.isChecked()) {
                    medicinebinding.CheckBoxBeflam.setEnabled(false);
                    medicinebinding.CheckBoxBrufin.setEnabled(false);
                    medicinebinding.CheckBoxEmoxel.setEnabled(false);
                    medicinebinding.CheckBoxPanadol.setEnabled(false);
                    medicinebinding.CheckBoxSoftin.setEnabled(false);

                } else {
                    medicinebinding.CheckBoxBeflam.setEnabled(true);
                    medicinebinding.CheckBoxBrufin.setEnabled(true);
                    medicinebinding.CheckBoxDesprin.setEnabled(true);
                    medicinebinding.CheckBoxEmoxel.setEnabled(true);
                    medicinebinding.CheckBoxPanadol.setEnabled(true);
                    medicinebinding.CheckBoxSoftin.setEnabled(true);
                }
            }
        });
//        medicinebinding.medRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                RadioButton button = (RadioButton) radioGroup.findViewById(i);
//                medicine = button.getText().toString();
//            }
//        });

        medicinebinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               if (medicinebinding.CheckBoxBeflam.isChecked()) {
                    medicine = "Beflam";
                    //selectedMedicines.add(medicine);
                }
                if (medicinebinding.CheckBoxBrufin.isChecked()) {
                    medicine = "Brufin";
                    ///selectedMedicines.add(medicine);
                }

                if (medicinebinding.CheckBoxDesprin.isChecked()) {
                    medicine = "Desprin";
                    //selectedMedicines.add(medicine);
                }
                if (medicinebinding.CheckBoxEmoxel.isChecked()) {
                    medicine = "Emoxel";
                    //selectedMedicines.add(medicine);
                }
                if (medicinebinding.CheckBoxPanadol.isChecked()) {
                    medicine = "Panadol";
                    //selectedMedicines.add(medicine);

                }
                if (medicinebinding.CheckBoxSoftin.isChecked()) {
                    medicine = "Softin";
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

    private void showDurationDialog() {
        durationDialog = new Dialog(this);
        durationPopupBinding = durationPopupBinding.inflate(getLayoutInflater());
        durationDialog.setContentView(durationPopupBinding.getRoot());
        durationDialog.show();

        durationPopupBinding.CheckBox3Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (durationPopupBinding.CheckBox3Days.isChecked()) {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                } else {
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
                if (durationPopupBinding.CheckBox5Days.isChecked()) {
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                } else {
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
                if (durationPopupBinding.CheckBox10Days.isChecked()) {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                    durationPopupBinding.CheckBox15Days.setEnabled(false);
                } else {
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
                if (durationPopupBinding.CheckBox15Days.isChecked()) {
                    durationPopupBinding.CheckBox5Days.setEnabled(false);
                    durationPopupBinding.CheckBox10Days.setEnabled(false);
                    durationPopupBinding.CheckBox3Days.setEnabled(false);
                } else {
                    durationPopupBinding.CheckBox3Days.setEnabled(true);
                    durationPopupBinding.CheckBox5Days.setEnabled(true);
                    durationPopupBinding.CheckBox10Days.setEnabled(true);
                    durationPopupBinding.CheckBox15Days.setEnabled(true);
                }
            }
        });

        durationPopupBinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (durationPopupBinding.CheckBox3Days.isChecked())
                    Duration = "3 Days";
                if (durationPopupBinding.CheckBox5Days.isChecked())
                    Duration = "5 Days";
                if (durationPopupBinding.CheckBox10Days.isChecked())
                    Duration = "10 Days";
                if (durationPopupBinding.CheckBox15Days.isChecked())
                    Duration = "15 Days";
                //Toast.makeText(PatientsDetailedActivity.this,"Duration :"+Duration,Toast.LENGTH_LONG).show();
                durationDialog.dismiss();
            }
        });


        durationPopupBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                durationDialog.dismiss();
            }
        });

    }

    private void showTimingDialog() {
        timingDialog = new Dialog(this);
        timingPopupBinding = timingPopupBinding.inflate(getLayoutInflater());
        timingDialog.setContentView(timingPopupBinding.getRoot());
        timingDialog.show();
        timingPopupBinding.CheckBoxAllOfThem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (timingPopupBinding.CheckBoxAllOfThem.isChecked()) {
                    timingPopupBinding.CheckBoxEvening.setEnabled(false);
                    timingPopupBinding.CheckBoxAfternoon.setEnabled(false);
                    timingPopupBinding.CheckBoxMorning.setEnabled(false);
                } else {
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

                if (timingPopupBinding.CheckBoxAllOfThem.isChecked()) {
                    Timing = "Morning-Afternoon and Evening";
                }
                if (timingPopupBinding.CheckBoxEvening.isChecked()) {
                    Timing = "Evening";
                }
                if (timingPopupBinding.CheckBoxMorning.isChecked()) {
                    Timing = "Morning";
                }
                if (timingPopupBinding.CheckBoxAfternoon.isChecked()) {
                    Timing = "Afternoon";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientsDetailedBinding.inflate(getLayoutInflater());



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        binding.listviewpatientmedicnes.setAdapter(adapter);
        binding.medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMedicineDialog();
                if (medicinebinding.CheckBoxSoftin.isChecked()) {
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
                String time = Timing;
                String med = medicine;
                String duraton = Duration;
                if (med == "" || duraton == "" || time == "") {
                    Toast.makeText(PatientsDetailedActivity.this, "Please select a medicine", Toast.LENGTH_LONG).show();
                } else
                {
//                    else {
//                    // Check if the selected medicine contradicts with the timing
//                    boolean hasContradiction = false;
//                    if (time.equals("Night") && med.equals("Panadol")) {
//                        hasContradiction = true;
//                    }
//
//                    if (hasContradiction) {
//                        Toast.makeText(PatientsDetailedActivity.this, "Contradiction: Do not take Panadol at night", Toast.LENGTH_LONG).show();
//                    } else {
                    String prescription = "Timing: " + time + ", Medicine: " + med + ", Duration: " + duraton;// Check if the medicine already exists in the adapter
                    boolean isAlreadyAdded = false;
                    for (int i = 0; i < adapter.getCount(); i++) {
                        String item = adapter.getItem(i);
                        if (item.contains(med)) {
                            isAlreadyAdded = true;
                            break;
                        }
                    }
                    if (isAlreadyAdded)
                        Toast.makeText(PatientsDetailedActivity.this, "This Medicine is already added", Toast.LENGTH_LONG).show();
                    else {
                        adapter.add(prescription);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        Intent i = getIntent();

        newCaseResponse = i.getStringExtra("newCaseResponse");

        try {
            JSONArray rootObj = new JSONArray(newCaseResponse);
            JSONObject firstItem = rootObj.getJSONObject(0);
            // Getting Patient Details From P Obj
            JSONObject pObj = firstItem.getJSONObject("p");
            JSONObject vitalObj = firstItem.getJSONObject("v");
            JSONObject visitsObj = firstItem.getJSONObject("x");
            visitId = visitsObj.getInt("visit_id");
            vitalsId=vitalObj.getInt("vitalID");
            patient_id=pObj.getInt("patient_id");
            jrdoc_id=visitsObj.getInt("jrdoc_id");
         //   Toast.makeText(PatientsDetailedActivity.this,"doc id"+pObj.getString("jrdoc_id"),Toast.LENGTH_LONG).show();
            if(!pObj.getString("jrdoc_id").equals("null")) {
                binding.followup.setVisibility(View.GONE);
                binding.removefollowup.setVisibility(View.VISIBLE);
            }

            else {
                binding.followup.setVisibility(View.VISIBLE);
                binding.removefollowup.setVisibility(View.GONE);
            }
//            if(pObj.getString("jrdoc_id").equals(null))
//                binding.removefollowup.setVisibility(View.GONE);
//            else
//                binding.removefollowup.setVisibility(View.VISIBLE);
            setContentView(binding.getRoot());

            binding.textviewPatientDOB.setText(pObj.getString("dob"));
            binding.textviewGender.setText(pObj.getString("gender"));
            binding.textviewBP.setText(vitalObj.getString("blood_pressure"));
            binding.textviewSugar.setText(vitalObj.getString("sugar"));
            binding.textviewTemp.setText(vitalObj.getString("temperature"));
            binding.textviewSymptoms.setText(vitalObj.getString("symptoms"));
            if (!pObj.getString("relation").equals("Self"))
                binding.textviewPatientname.setText(pObj.getString("relative_name"));
            else
                binding.textviewPatientname.setText(pObj.getString("full_name"));
            binding.followup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        GetRetrofitInstance.getApiService().AddFollowUp(patient_id,jrdoc_id).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.isSuccessful())
                                Toast.makeText(PatientsDetailedActivity.this,"Follow up Added",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(PatientsDetailedActivity.this,"Follow up not Added",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                }
            });
            binding.removefollowup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetRetrofitInstance.getApiService().RemoveFollowUp(patient_id).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful())
                                Toast.makeText(PatientsDetailedActivity.this,"Removed Follow up",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PatientsDetailedActivity.this,"Not removed Follow up",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });

//            Toast.makeText(PatientsDetailedActivity.this,"Image"+vitalObj.getString("image"),Toast.LENGTH_LONG).show();
//            if (vitalObj.has("image") && !pObj.getString("image").equals("")) {
//                Glide.with(binding.imagesss).load(Api.BASE_URL2 + vitalObj.getString("image")).into(binding.imagesss);
//            }
            Glide.with(binding.imagesss).load(Api.BASE_URL2 + vitalObj.getString("image")).into(binding.imagesss);
            Glide.with(binding.testimagesss).load(Api.BASE_URL3 + vitalObj.getString("testimage")).into(binding.testimagesss);
            GetRetrofitInstance.getApiService().Gettingappointmentid(pObj.getInt("patient_id")).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        id = Integer.parseInt(response.body());
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        } catch (Exception e) {

        }


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Prescription> prescriptions = new ArrayList<>();
                if(binding.listviewpatientmedicnes.getAdapter().getCount()==0)
                {
                    Toast.makeText(PatientsDetailedActivity.this, "Please add prescription", Toast.LENGTH_SHORT).show();
                }
                else {

                    for (int i = 0; i < adapter.getCount(); i++) {
                        String timing = adapter.getItem(i).split(",")[0].split(":")[1].trim();
                        String duration = "" + adapter.getItem(i).split(",")[2].split(" ")[2] + " Days";
                        String med = adapter.getItem(i).split(",")[1].split(":")[1].trim();
                        int idd = id;
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1; // Note: months are zero-based
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        String currentDateStr = day + "/" + month + "/" + year;
                        Prescription prescription = new Prescription(idd, med, duration, timing, currentDateStr);
                        prescriptions.add(prescription);
                    }
                    String comments="";
                    comments=binding.textviewComments.getText().toString();
                    GetRetrofitInstance.getApiService().CommentsTest(id,comments).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
//                            if(response.isSuccessful())
//                                Toast.makeText(PatientsDetailedActivity.this, "Comments Add", Toast.LENGTH_LONG).show();
//                            else
//                                Toast.makeText(PatientsDetailedActivity.this, "Comments not Add", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(PatientsDetailedActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                    GetRetrofitInstance.getApiService().Addingprescription(prescriptions).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(PatientsDetailedActivity.this, "Patients Prescription Send", Toast.LENGTH_LONG).show();
                                Intent ii = new Intent(PatientsDetailedActivity.this, JrDocLoginTestActivity.class);
                                ii.putExtra("Docfull_name", Docfull_name);
                                ii.putExtra("Docid", Docid);
                                setResult(RESULT_OK, ii);
                                finish();
                                // Toast.makeText(PatientsDetailedActivity.this,"Patients id you want to send "+patient_id,Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(PatientsDetailedActivity.this, "Patients Prescription not Send" + response.code(), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(PatientsDetailedActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    GetRetrofitInstance.getApiService().Updatingvitalstatus(vitalsId,id).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
//                            if (response.isSuccessful())
//                                Toast.makeText(PatientsDetailedActivity.this, "vitals updated", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                }
            }
        });
        //Toast.makeText(PatientsDetailedActivity.this,"This is : "+patient_id,Toast.LENGTH_LONG).show();
    }
}