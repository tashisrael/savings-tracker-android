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

import java.util.ArrayList;

public class MySavingsAdapter extends RecyclerView.Adapter<MySavingsAdapter.MySavingsViewHolder> {

    private ArrayList<MysavingsModelClass> mSavingsList;
    Context context;

    public static class MySavingsViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public ProgressBar progressBar1;
        public TextView mTextView7;
        public TextView mTextView8;

        LinearLayout mainLayout;

        public MySavingsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1= itemView.findViewById(R.id.itemName);
            mTextView2=itemView.findViewById(R.id.percentAge);
            mTextView3= itemView.findViewById(R.id.itemName2);
            mTextView4= itemView.findViewById(R.id.itemOverdue);
            mTextView5= itemView.findViewById(R.id.itemOverdue2);
            progressBar1=itemView.findViewById(R.id.progress1);
            mTextView6=itemView.findViewById(R.id.border);
            mTextView7=itemView.findViewById(R.id.micurrencyitem);
            mTextView8=itemView.findViewById(R.id.itemmicurrency2);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

    public MySavingsAdapter (Context context,ArrayList<MysavingsModelClass>savingsList){
             this.context=context;
             mSavingsList= savingsList;
    }

    @NonNull
    @Override
    public MySavingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View V = inflater.inflate(R.layout.mysavings_item,null);
        MySavingsViewHolder evh = new MySavingsViewHolder(V);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MySavingsViewHolder holder, int position) {
             MysavingsModelClass currentItem= mSavingsList.get(position);
             holder.mTextView1.setText(currentItem.getItemTitle());
             holder.mTextView2.setText(currentItem.getItemDesc());
             holder.mTextView3.setText(currentItem.getDateText());
             holder.mTextView4.setText(currentItem.getItemAmount());
             holder.mTextView5.setText(currentItem.getStartingAmount());
             holder.mTextView6.setText(currentItem.getStartingdate());
             holder.progressBar1.setProgress(currentItem.getProgress());
             holder.mTextView7.setText(currentItem.getMicurrency());
            holder.mTextView8.setText(currentItem.getMiCurrency2());



             holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, SecondActivity.class);
                     intent.putExtra("data1",currentItem.getItemTitle());
                     intent.putExtra("data2",currentItem.getItemDesc());
                     intent.putExtra("data3",currentItem.getDateText());
                     intent.putExtra("data4",currentItem.getItemAmount());
                     intent.putExtra("data5",currentItem.getStartingAmount());
                     intent.putExtra("data6",currentItem.getStartingdate());
                     intent.putExtra("data7",currentItem.getMicurrency());
                     context.startActivity(intent);

                 }
             });

    }

    @Override
    public int getItemCount() {
        return mSavingsList.size();
    }
}
