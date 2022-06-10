package com.mobil.odev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RankAdapter extends ArrayAdapter<rank> {

    Context context;
    List<rank> arrayListRank;

    public RankAdapter(@NonNull Context context, List<rank> arrayListRank) {
        super(context, R.layout.custom_ranklist_item, arrayListRank);

        this.context = context;
        this.arrayListRank =arrayListRank;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ranklist_item, null, true);
        TextView tvlogo = view.findViewById(R.id.tvlogo);
        TextView tvname = view.findViewById(R.id.tvrankname);
        TextView tvnet = view.findViewById(R.id.tvranknet);
        TextView tvcode = view.findViewById(R.id.tvrankcode);
        tvlogo.setText(arrayListRank.get(position).getID());
        tvname.setText(arrayListRank.get(position).getUsername());
        tvnet.setText(arrayListRank.get(position).getNet());
        tvcode.setText(arrayListRank.get(position).getCode());
        return view;

    }
}
