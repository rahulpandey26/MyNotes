package com.example.mynotes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.mynotes.R;
import com.example.mynotes.database.Note;

public class AddNotesFragment extends BaseFragment implements View.OnClickListener {

    private AddNoteScreenListener mListener;
    private EditText mTitleEditText;
    private EditText mDescEditText;
    private TextView mSaveBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_add_note);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (AddNoteScreenListener) context;
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
    }

    private void initializeViews(View view) {
        mTitleEditText = view.findViewById(R.id.title);
        mDescEditText = view.findViewById(R.id.description);
        mSaveBtn = view.findViewById(R.id.save_btn);
        mSaveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_btn:
                saveNote();
                break;
        }
    }

    private void saveNote() {
        String title = mTitleEditText.getText().toString().trim();
        String desc = mDescEditText.getText().toString().trim();
        Note note =  new Note();
        note.setNoteTitle(title);
        note.setNoteDescription(desc);
        mListener.onSaveBtnClick(note);

    }

    public interface AddNoteScreenListener{
        void onSaveBtnClick(Note note);
    }
}
