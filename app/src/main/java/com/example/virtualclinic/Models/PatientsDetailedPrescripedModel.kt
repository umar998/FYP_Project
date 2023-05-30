package com.example.virtualclinic.Models

import com.google.gson.annotations.SerializedName

data class PatientsDetailedPrescripedModel(
    @SerializedName("x") val patientX: MyPatientX,
    @SerializedName("p") val myPatientP: MyPatientP,
    @SerializedName("t") val myPatientT: MyPatientT
) : java.io.Serializable

data class MyPatientX(
    @SerializedName("appointment_id") val aptDate: Int,
    @SerializedName("patient_id") val patient_id: Int,
    @SerializedName("jrdoc_id") val jrdoc_id: Int,
    @SerializedName("rating") val rating: Float,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("status") val status: Int,
    @SerializedName("srdoc_id") val srdoc_id: Int,
    @SerializedName("visit_id") val visit_id: Int,
    @SerializedName("shown") val shown: Int,
    @SerializedName("nurseID") val nurseID: Int,
) : java.io.Serializable

data class MyPatientP(
    @SerializedName("prescription_id") val prescription_id: Int,
    @SerializedName("appointment_id") val appointment_id: Int,
    @SerializedName("medicine_name") val medicine_name: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("timings") val timings: String,
    @SerializedName("date") val date: String,
) : java.io.Serializable
data class MyPatientT(
    @SerializedName("commentTest_id") val commentTest_id: Int,
    @SerializedName("comments") val comments: String,
    @SerializedName("appointment_id") val appointment_id: Int

);

