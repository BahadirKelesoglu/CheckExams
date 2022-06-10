package com.mobil.odev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends ArrayAdapter<exams> {
    Context context;
    List<exams> arraylistExams;
    public MyAdapter(@NonNull Context context, List<exams> arraylistExams) {
        super(context, R.layout.custom_list_item, arraylistExams);
        this.context = context;
        this.arraylistExams = arraylistExams;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);
        TextView tvID = view.findViewById(R.id.tvexam);
        TextView tvexam2 = view.findViewById(R.id.tvexam2);
        TextView tvtrue = view.findViewById(R.id.tvexam4);
        TextView tvfalse = view.findViewById(R.id.tvexam3);
        TextView tvrankcode = view.findViewById(R.id.tvexamrankcode);
        TextView tvexamtotal = view.findViewById(R.id.tvexamtotal);

        tvID.setText(arraylistExams.get(position).getID());
        tvexam2.setText("NET = " + arraylistExams.get(position).getNet());
        tvtrue.setText("DOGRU = " +arraylistExams.get(position).getTrue());
        tvfalse.setText("YANLİS = " +arraylistExams.get(position).getFalse());
        tvrankcode.setText(arraylistExams.get(position).getCode());
        tvexamtotal.setText("TOTAL = " + arraylistExams.get(position).getTotal());


        return view;
    }
}
