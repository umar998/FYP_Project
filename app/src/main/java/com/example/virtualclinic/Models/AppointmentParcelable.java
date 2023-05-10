//package com.example.virtualclinic.Models;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class AppointmentParcelable implements Parcelable {
//    private SrDocAppointments appointment;
//
//    public AppointmentParcelable(SrDocAppointments appointment) {
//        this.appointment = appointment;
//    }
//
//    protected AppointmentParcelable(Parcel in) {
//        // Read the fields from the Parcel and assign them to the appointment object
//        // Example:
//        // appointment = new SrDocAppointments();
//        // appointment.setVisit_id(in.readInt());
//        // appointment.setAppointment_id(in.readInt());
//        // ... (read other fields)
//
//        // Make sure to implement this constructor according to your SrDocAppointments class structure
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        // Write the fields of the appointment object to the Parcel
//        // Example:
//        // dest.writeInt(appointment.getVisit_id());
//        // dest.writeInt(appointment.getAppointment_id());
//        // ... (write other fields)
//
//        // Make sure to implement this method according to your SrDocAppointments class structure
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<AppointmentParcelable> CREATOR = new Creator<AppointmentParcelable>() {
//        @Override
//        public AppointmentParcelable createFromParcel(Parcel in) {
//            return new AppointmentParcelable(in);
//        }
//
//        @Override
//        public AppointmentParcelable[] newArray(int size) {
//            return new AppointmentParcelable[size];
//        }
//    };
//}
