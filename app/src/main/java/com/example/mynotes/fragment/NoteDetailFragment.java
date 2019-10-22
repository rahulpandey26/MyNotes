package com.example.mynotes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynotes.R;
import com.example.mynotes.database.Note;

public class NoteDetailFragment extends BaseFragment {

    private TextView mTitleName;
    private TextView mDescription;
    private Note mNotes;

    public NoteDetailFragment(Note note) {
        mNotes = note;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_note_detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setUiData();
    }

    private void initializeViews(View view) {
        mTitleName = view.findViewById(R.id.title_txt);
        mDescription = view.findViewById(R.id.description_txt);
    }

    private void setUiData() {
        if(mNotes != null) {
            mTitleName.setText(mNotes.getNoteTitle());
            mDescription.setText(mNotes.getNoteDescription());
        }
    }

}
