package com.example.mynotes.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotes.R;
import com.example.mynotes.adapter.HomeNotesAdapter;
import com.example.mynotes.database.Note;
import com.example.mynotes.database.NoteViewModel;

import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener,
        HomeNotesAdapter.HomeNoteListClickListener {

    private NoteViewModel mNoteViewModel;
    private RecyclerView mNotesRecyclerView;
    private HomeNotesAdapter mAdapter;
    private HomeScreenListener mListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (HomeScreenListener) context;
        } catch (ClassCastException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        updateLiveData();
        setAdapter();
    }

    private void initializeViews(View view) {
        mNotesRecyclerView = view.findViewById(R.id.note_list);
        mAdapter = new HomeNotesAdapter(this);
        view.findViewById(R.id.fab_btn).setOnClickListener(this);
    }

    private void updateLiveData() {
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // update recycler view
                mAdapter.setNotes(notes);
            }
        });
    }

    private void setAdapter() {
        mNotesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mNotesRecyclerView.setAdapter(mAdapter);
    }

    public void saveNotes(Note note){
        mNoteViewModel.insert(note);
    }

    public void updateNotes(Note note){
        mNoteViewModel.update(note);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_btn:
                mListener.onAddBtnClick();
                break;
        }
    }

    @Override
    public void onNoteClickListener(Note note) {
        mListener.openNoteDetailsScreen(note);
    }

    @Override
    public void onDeleteClickListener(Note note) {
        mNoteViewModel.delete(note);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_LONG).show();
    }

    public interface HomeScreenListener{
        void onAddBtnClick();

        void openNoteDetailsScreen(Note note);
    }
}
