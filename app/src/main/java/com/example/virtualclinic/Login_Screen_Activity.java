package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.virtualclinic.Models.JuniorDoctorLogin;
import com.example.virtualclinic.Models.Nurse;
import com.example.virtualclinic.Models.SeniorDoctorLogin;
import com.example.virtualclinic.Models.StaticClass;
import com.example.virtualclinic.api.Api;
import com.example.virtualclinic.api.RetrofitClient;
import com.example.virtualclinic.databinding.ActivityLoginScreenBinding;
import com.example.virtualclinic.databinding.AdminLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Screen_Activity extends AppCompatActivity {
    ActivityLoginScreenBinding binding;
    private Dialog adminDialog;
    TextInputEditText email, password;
    private AdminLoginBinding adminLoginBinding;
    public static String docName;
    String username = "admin";
    String passsword = "admin";

    private void showAdminDialog() {
        adminDialog = new Dialog(this);
        adminLoginBinding = AdminLoginBinding.inflate(getLayoutInflater());
        adminDialog.setContentView(adminLoginBinding.getRoot());
        adminDialog.show();
        adminLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String getusername = adminLoginBinding.edittextUsername.getText().toString();
                String getpassword = adminLoginBinding.edittextPassword.getText().toString();
                if (getusername.equalsIgnoreCase(username) && getpassword.equalsIgnoreCase(passsword)) {
                    Intent i = new Intent(Login_Screen_Activity.this, AdminLayoutActivity.class);
                    startActivity(i);
                } else
                    Toast.makeText(Login_Screen_Activity.this, "Incorrect Username/Password", Toast.LENGTH_LONG).show();

            }
        });
        adminLoginBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = findViewById(R.id.edittext_username);
        password = findViewById(R.id.edittext_password);
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Screen_Activity.this,
                        Signup_Screen_Activity.class);
                startActivity(i);
            }
        });
        binding.admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdminDialog();
            }
        });
        binding.patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Screen_Activity.this,
                        SearchingPatientCNICActivity.class);
                startActivity(i);
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText().toString()) || (TextUtils.isEmpty(email.getText().toString()))) {
                    Toast.makeText(Login_Screen_Activity.this,
                            "username/password required", Toast.LENGTH_LONG).show();
                } else {

                    Login();
                    //Toast.makeText(Login_Screen_Activity.this,"Welcomm" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Login() {
        String username = binding.edittextUsername.getText().toString();
        String password = binding.edittextPassword.getText().toString();
        RetrofitClient client =
                RetrofitClient.getInstance();
        Api api = client.getMyApi();
        Call<Nurse> call = api.NurseLogin(username, password);
        call.enqueue(new Callback<Nurse>() {
            @Override
            public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                if (response.isSuccessful()) {
                    Nurse n = response.body();
                    String full_name = n.getFull_name();
                    StaticClass.id = n.getNurse_ID();
                    Toast.makeText(Login_Screen_Activity.this,
                            "Welcome " + full_name, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_Screen_Activity.this, MainBottomTabsActivity.class);
                    i.putExtra("nurseID", StaticClass.id);
                    startActivity(i);
                } else {
                    Jrlogin();

                }
            }

            @Override
            public void onFailure(Call<Nurse> call, Throwable t) {
            }
        });

        //  Toast.makeText(Login_Screen_Activity.this,"Welcomm"+role , Toast.LENGTH_LONG).show();


//        JuniorDoctorLogin j = new JuniorDoctorLogin();
//        j.Email = username;
//        j.Password = password;
//        j.role = role;

    }

    public void Jrlogin() {
        String username = binding.edittextUsername.getText().toString();
        String password = binding.edittextPassword.getText().toString();
        RetrofitClient client =
                RetrofitClient.getInstance();
        Api api = client.getMyApi();
        Call<JuniorDoctorLogin> calls = api.JrLogin(username, password);
        calls.enqueue(new Callback<JuniorDoctorLogin>() {
            @Override
            public void onResponse(Call<JuniorDoctorLogin> call, Response<JuniorDoctorLogin> response) {
                if (response.isSuccessful()) {
                    JuniorDoctorLogin j = response.body();
                    StaticClass.id = j.getJrdoc_id();
                    StaticClass.docName = j.getFull_name();
                    Toast.makeText(Login_Screen_Activity.this,
                            "Welcome " + StaticClass.docName + StaticClass.id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_Screen_Activity.this, JrDocLoginTestActivity.class);
                    i.putExtra("jrdoc_id", StaticClass.id);
                    i.putExtra("full_name", StaticClass.docName);
                    startActivity(i);
                } else {
                    Srlogin();
                }
            }

            @Override
            public void onFailure(Call<JuniorDoctorLogin> call, Throwable t) {
                Toast.makeText(Login_Screen_Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Srlogin() {
        String username = binding.edittextUsername.getText().toString();
        String password = binding.edittextPassword.getText().toString();
        RetrofitClient client =
                RetrofitClient.getInstance();
        Api api = client.getMyApi();
        Call<SeniorDoctorLogin> call = api.Srdoclogin(username, password);
        call.enqueue(new Callback<SeniorDoctorLogin>() {
            @Override
            public void onResponse(Call<SeniorDoctorLogin> call, Response<SeniorDoctorLogin> response) {
                if (response.isSuccessful()) {
                    SeniorDoctorLogin s = response.body();
                    StaticClass.id = s.getSrdoc_id();
                    StaticClass.docName = s.getFull_name();
                    Intent i = new Intent(Login_Screen_Activity.this, SrDocLoginActivity.class);
                    i.putExtra("Srdoc_id", StaticClass.id);
                    i.putExtra("full_name", StaticClass.docName);
                    startActivity(i);
                }
                else
                    Toast.makeText(Login_Screen_Activity.this,"Enter Correct username/Password" , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SeniorDoctorLogin> call, Throwable t) {
                Toast.makeText(Login_Screen_Activity.this, "Login Failed", Toast.LENGTH_LONG).show();

            }
        });

    }
}