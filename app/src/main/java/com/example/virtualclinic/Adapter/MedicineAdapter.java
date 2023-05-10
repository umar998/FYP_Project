package com.example.virtualclinic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.virtualclinic.R;

import java.util.List;

public class MedicineAdapter extends ArrayAdapter<String> {
    private List<String> medicineList;

    public MedicineAdapter(Context context, List<String> medicineList) {
        super(context, 0, medicineList);
        this.medicineList = medicineList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String medicine = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medicne_popup, parent, false);
        }

//        TextView medicineTextView = convertView.findViewById(R.id.medicine_text_view);
//        medicineTextView.setText(medicine);

        return convertView;
    }
}
