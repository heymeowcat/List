package com.heymeowcat.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final ArrayList<String> mids;
    private final ArrayList<String> mtitles;
    private final OnNotelistener monnotelistener;

    public RecyclerViewAdapter(ArrayList<String> mids, ArrayList<String> mtitles,OnNotelistener onNotelistener) {
        this.mids = mids;
        this.mtitles = mtitles;
        this.monnotelistener=onNotelistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view,monnotelistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.titletextview.setText(mtitles.get(position));
    }


    @Override
    public int getItemCount() {
        return mids.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView titletextview;
        final OnNotelistener onNotelistener;

        ViewHolder(@NonNull View itemView, OnNotelistener onNotelistener) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.todottitle);
            this.onNotelistener=onNotelistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNotelistener.ontaskclick(getAdapterPosition());
        }
    }

    public interface OnNotelistener{
        void ontaskclick(int position);

    }

}
