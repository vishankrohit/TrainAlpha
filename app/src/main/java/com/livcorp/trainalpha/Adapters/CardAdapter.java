package com.livcorp.trainalpha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livcorp.trainalpha.Models.CardModel;
import com.livcorp.trainalpha.PreviewActivity;
import com.livcorp.trainalpha.R;

import java.util.ArrayList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.viewHolder>{


    ArrayList<CardModel> list;
    Context context;
    int level;

    public CardAdapter(ArrayList<CardModel> list, Context context, int level) {
        this.list = list;
        this.context = context;
        this.level = level;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview_one,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CardModel model = list.get(position);
        holder.workoutName.setText(model.getWorkoutName());
        holder.cardBackground.setImageResource(model.getBg());
        holder.workoutTime.setText(model.getWorkoutTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isPointsValid = true;
                boolean isMainActivity = true;
                Intent intent = new Intent(context, PreviewActivity.class);
                intent.putExtra("Bg", model.getBg());
                intent.putExtra("WorkoutName",model.getWorkoutName());
                intent.putExtra("WorkoutTime",model.getWorkoutTime());
                intent.putExtra("Color" ,model.getColor());
                intent.putExtra("position",position);
                intent.putExtra("level",level);
                intent.putExtra("Difficulty",model.getDifficulty());
                intent.putExtra("Table", model.getTableName());;
                intent.putExtra("Points",model.getPoints());
                intent.putExtra("isPointsValid",isPointsValid);
                intent.putExtra("isMainActivity",isMainActivity);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView cardBackground;
        TextView workoutName;
        TextView workoutTime;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cardBackground = itemView.findViewById(R.id.cardBackground);
            workoutName = itemView.findViewById(R.id.workoutName);
            workoutTime = itemView.findViewById(R.id.workoutTime);
        }
    }
}
