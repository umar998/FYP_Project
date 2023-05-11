package com.example.virtualclinic.api;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.virtualclinic.FileUtil;
import com.example.virtualclinic.Models.AppointmentDataNew;
import com.example.virtualclinic.Models.JuniorDoctorLogin;
import com.example.virtualclinic.Models.Nurse;
import com.example.virtualclinic.Models.NurseSignup;
import com.example.virtualclinic.Models.Patient;
import com.example.virtualclinic.Models.PatientObject;
import com.example.virtualclinic.Models.Prescription;
import com.example.virtualclinic.Models.SeniorDoctorLogin;
import com.example.virtualclinic.Models.SrDocAppointments;
import com.example.virtualclinic.Models.SrDocSignup;
import com.example.virtualclinic.Models.juniorDoctor;
import com.example.virtualclinic.Models.StaticClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    public  static String BASE_URL="http://192.168.180.198/fyp/api/";

    @POST("Jrdoc/Jrsignup")
    public Call<String> Jrsignup(
        @Body juniorDoctor j
        );

    @POST("Admin/Addnewnurse")
    public Call<String> Addnewnurse(
            @Body NurseSignup n
    );

    @POST("Admin/Addnewsrdoc")
    public Call<String> Addnewsrdoc(
            @Body SrDocSignup j
    );
    @GET("Srdoc/AppointmentDetails")
    Call<ArrayList<AppointmentDataNew>> AppointmentDetails(
            @Query("visitid") int visitid
    );
    @GET("Srdoc/Getpresdetails")
            Call<List<Prescription>> Getpresdetails(@Query("aptid") int aptid
        );

    @POST("Srdoc/DoneAppointment")
    public  Call<String> DoneAppointment(
            @Query("aptid") int aptid ,@Query("rating") float rating,@Query("patid") int patid
    );


    @POST("JrDoc/Addingprescription")
    public Call<String> Addingprescription(
            @Body List<Prescription> prescriptions
            );
    @POST("Patient/Updatepatdetails")
    public  Call<String> Updatepatdetails(
            @Body Patient p , @Query("patient_id") int patient_id , @Query("newcnic") String  newcnic
    );
    @GET("Nursel/Nurselogin")
    public Call<Nurse> NurseLogin(
            @Query("email") String email,@Query("password") String password
    );
    @POST("Jrdoc/Jrlogin")
    public Call<JuniorDoctorLogin> JrLogin(
            @Query("email") String email,@Query("password") String password
    );
    @POST("Srdoc/Srdoclogin")
    public Call<SeniorDoctorLogin> Srdoclogin(
            @Query("email") String email,@Query("password") String password
    );
    @GET("Jrdoc/Gettingappointmentid")
    public Call<String> Gettingappointmentid(
            @Query("patid") int patid
    );
    @POST("Jrdoc/Appointment")
    public Call<String> Appointment(
            @Query("patid") int patid,@Query("jrdocid") int jrdocid,@Query("visitid") int visitid
    );
    @POST("Patient/Addpatient")
    public Call<String> Addpatient(
            @Body Patient p
    );
    @GET("Patient/GetAllPrescriptions")
    Call<List<Prescription>> GetAllPrescriptions(
            @Query("cnic") String cnic
    );
    @GET("Jrdoc/MyNewCases")
    public Call<String> MyNewCases(
            @Query("id") int id
    );
    @GET("Srdoc/MyNewAppointments")
    public Call<ArrayList<SrDocAppointments>> MyNewAppointments(
            @Query("id") int id
    );
    @GET("Srdoc/AssignAppointmentsToSrDoctor")
     Call<Void> AssignAppointmentsToSrDoctor();


    @GET("Jobs/AssignPatientToDoctor")
    Call<Void> AssignPatientToDoctor();

    @POST("Jrdoc/Updatingvitalstatus")
    public Call<String> Updatingvitalstatus(
            @Query("vitalid") int vitalid
    );
    @POST("Jrdoc/Jrlogout")
    public Call<String> Jrlogout(
            @Query("jrdocid") int jrdocid
    );
    @POST("Srdoc/Srlogout")
    public Call<String> Srlogout(
            @Query("srdocid") int srdocid
    );

    @POST("JrDoc/AcceptedCase")
    public Call<String> AcceptedCase(
            @Query("jrdocid") int jrdocid , @Query("patid") int patid, @Query("visitid") int visitid
    );


    @Multipart
    @POST("Nursel/Addvitals")
    public Call<String> Addvitals(
            @Part MultipartBody.Part photo,
            @Part("patient_id") RequestBody id,
            @Part("blood_pressure") RequestBody  bp,
            @Part("sugar") RequestBody  sugar,
            @Part("temperature") RequestBody  temp,
            @Part("Symptoms") RequestBody  symp


    );
    @Multipart
    @POST("Nursel/upload")
    Call<ResponseBody> Upload(
            @Part MultipartBody.Part photo,
            @Part("patient_id") RequestBody id,
            @Part("blood_pressure") RequestBody bp,
            @Part("sugar") RequestBody sugar,
            @Part("temperature") RequestBody temp,
            @Part("Symptoms") RequestBody symp
    );

    @POST("Patient/Checkcnic")
    Call<PatientObject> Checkcnic(@Query("cnic") String cnic);

    @POST("Patient/Visits")
    Call<StaticClass> Visits(@Query("patient_id") int patient_id);






    @NonNull
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException
    {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    public default RequestBody createPartFromString(String descriptionString)
    {
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return  description;

    }
}
