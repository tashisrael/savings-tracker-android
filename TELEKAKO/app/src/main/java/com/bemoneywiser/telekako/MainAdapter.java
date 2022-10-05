package com.bemoneywiser.telekako;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.Goal.setText(model.getGoal());
        holder.Amount.setText(model.getAmount());
        holder.StartingAmt.setText(model.getStartingAmt());
        holder.Date.setText(model.getDate());
        holder.Currency.setText(model.getCurrency());
        holder.Percentage.setText(model.getPercentage());
        holder.Currency2.setText(model.getCurrency());
        holder.progressBar.setProgress(Integer.parseInt(model.getPercentage()));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("data1",model.getGoal());
                intent.putExtra("data2",model.getAmount());
                intent.putExtra("data3",model.getStartingAmt());
                intent.putExtra("data4",model.getDate());
                intent.putExtra("data5",model.getCurrency());
                intent.putExtra("data6",model.getPercentage());
                intent.putExtra("data7",model.getCurrency());
                context.startActivity(intent);

            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mysavings_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView Goal,Amount,StartingAmt,Date,Currency,Percentage, Currency2;
        ProgressBar progressBar;
        LinearLayout mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Goal = itemView.findViewById(R.id.itemName);
            Amount = itemView.findViewById(R.id.itemOverdue);
            StartingAmt = itemView.findViewById(R.id.itemOverdue2);
            Date = itemView.findViewById(R.id.itemName2);
            Currency = itemView.findViewById(R.id.micurrencyitem);
            Percentage = itemView.findViewById(R.id.percentAge);
            Currency2 = itemView.findViewById(R.id.itemmicurrency2);
            progressBar = itemView.findViewById(R.id.progress1);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
