package com.livcorp.trainalpha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livcorp.trainalpha.Models.PreviewModel;
import com.livcorp.trainalpha.R;

import java.util.ArrayList;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.viewHolder> {

    ArrayList<PreviewModel> list;
    Context context;

    public PreviewAdapter(ArrayList<PreviewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PreviewModel model = list.get(position);
        holder.tvExerciseName.setText(model.getExercise().getExerciseName());
        holder.tvReps.setText(model.getReps());
        holder.line.setBackgroundResource(model.getLineColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvExerciseName;
        TextView tvReps;
        RelativeLayout line;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvExerciseName = itemView.findViewById(R.id.tvExerciseNamePreview);
            tvReps = itemView.findViewById(R.id.tvRepsPreview);
            line = itemView.findViewById(R.id.lineLayout);
        }
    }
}
