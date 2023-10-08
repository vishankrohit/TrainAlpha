package com.livcorp.trainalpha.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livcorp.trainalpha.Helpers.DBHelper;
import com.livcorp.trainalpha.Models.CardModel;
import com.livcorp.trainalpha.PreviewActivity;
import com.livcorp.trainalpha.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DiscoverCardAdapter extends RecyclerView.Adapter<DiscoverCardAdapter.viewHolder> {

    ArrayList<CardModel> list;
    Context context;
    Dialog dialog;

    public DiscoverCardAdapter(ArrayList<CardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview_discover,parent,false);
        return new DiscoverCardAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CardModel model = list.get(position);
        holder.workoutName.setText(model.getWorkoutName());
        holder.cardBackground.setImageResource(model.getBg());
        holder.workoutTime.setText(model.getWorkoutTime());
        dialog = new Dialog(context);
        DBHelper helper = new DBHelper(context);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String ID = auth.getCurrentUser().getUid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = helper.getWorkoutDataById(ID);
                int PointsDatabase = c.getInt(4);
                int Cost = model.getPoints();
//                showDialogCard(model,PointsDatabase,Cost,helper,ID);
                switchActivity(model);
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
    public void showDialogCard(CardModel model, int UserPoints, int RequiredPoints, DBHelper helper, String ID){
        dialog.setContentView(R.layout.dialog_unlock_workout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnUsePoints = dialog.findViewById(R.id.btnUsePoints);
        TextView tvCostDialog = dialog.findViewById(R.id.tvCostDialog);
        tvCostDialog.setText("You need "+RequiredPoints+" points to unlock\nthis workout");
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        dialog.show();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnUsePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int OldPoints = UserPoints;
                int Cost = RequiredPoints;

                if (OldPoints>=Cost){

                    int NewPoints = OldPoints - Cost;
                    helper.updatePoints(ID,NewPoints);

                    Intent intent = new Intent(context, PreviewActivity.class);
                    intent.putExtra("Bg", model.getBg());
                    intent.putExtra("WorkoutName",model.getWorkoutName());
                    intent.putExtra("WorkoutTime",model.getWorkoutTime());
                    intent.putExtra("Color" ,model.getColor());
                    intent.putExtra("Difficulty",model.getDifficulty());
                    intent.putExtra("Table", model.getTableName());
                    intent.putExtra("Points",model.getPoints());
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "You do not have enough Points!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void switchActivity(CardModel model){
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra("Bg", model.getBg());
        intent.putExtra("WorkoutName",model.getWorkoutName());
        intent.putExtra("WorkoutTime",model.getWorkoutTime());
        intent.putExtra("Color" ,model.getColor());
        intent.putExtra("Difficulty",model.getDifficulty());
        intent.putExtra("Table", model.getTableName());
        intent.putExtra("Points",model.getPoints());
        context.startActivity(intent);
    }

}
