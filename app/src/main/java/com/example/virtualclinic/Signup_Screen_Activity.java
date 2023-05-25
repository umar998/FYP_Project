package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualclinic.Models.juniorDoctor;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivitySignupScreenBinding;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_Screen_Activity extends AppCompatActivity {
    ActivitySignupScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String Fullname = binding.edittextFullname.getText().toString();
                    String Fathername = binding.edittextFathername.getText().toString();
                    String DOB = binding.edittextDOB.getText().toString();
                    String username = binding.edittextUsername.getText().toString();
                    String password = binding.edittextPassword.getText().toString();
                    String gender = "Male";
                    if (binding.RadiaButtonFeMale.isChecked())
                        gender = "FeMale";
                    else if (binding.RadiaButtonMale.isChecked())
                        gender = "Male";
                    String role = "JrDoc";
                    String contact = binding.edittextContact.getText().toString();
                    RetrofitClient client =
                            RetrofitClient.getInstance();
                    Api api = client.getMyApi();
                    juniorDoctor j = new juniorDoctor();
                    j.full_name = Fullname;
                    j.father__name = Fathername;
                    j.dob = DOB;
                    j.email = username;
                    j.password = password;
                    j.gender = gender;
                    j.role = role;
                    j.contact = contact;

                    api.Jrsignup(j).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(Signup_Screen_Activity.this,
                                    "Signed Up",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(Signup_Screen_Activity.this, t.toString(), Toast.LENGTH_SHORT).show();


                        }
                    });
            }
        });
    }
}