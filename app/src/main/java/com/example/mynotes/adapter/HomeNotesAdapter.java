package com.example.mynotes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotes.R;
import com.example.mynotes.database.Note;

import java.util.ArrayList;
import java.util.List;

public class HomeNotesAdapter extends RecyclerView.Adapter<HomeNotesAdapter.ViewHolder> {

    private HomeNoteListClickListener mListener;
    private List<Note> mNotes = new ArrayList<>();

    public HomeNotesAdapter(HomeNoteListClickListener homeNoteListClickListener){
        mListener = homeNoteListClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item_layout, parent, false);
        return new HomeNotesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mNotes.size() > 0) {
            holder.mTitle.setText(mNotes.get(position).getNoteTitle());
            holder.mDesc.setText(mNotes.get(position).getNoteDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View mNoteItemLyt;
        private TextView mTitle;
        private TextView mDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoteItemLyt = itemView.findViewById(R.id.note_item_lyt);
            mTitle = itemView.findViewById(R.id.title);
            mDesc = itemView.findViewById(R.id.desc);
            mNoteItemLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNoteClickListener(mNotes.get(getAdapterPosition()));
                }
            });
            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDeleteClickListener(mNotes.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface HomeNoteListClickListener {
        void onNoteClickListener(Note note);

        void onDeleteClickListener(Note note);
    }
}
