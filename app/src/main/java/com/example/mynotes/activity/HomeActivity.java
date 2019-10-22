package com.example.mynotes.activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mynotes.R;
import com.example.mynotes.database.Note;
import com.example.mynotes.fragment.AddNotesFragment;
import com.example.mynotes.fragment.HomeFragment;
import com.example.mynotes.util.FragmentHelper;

public class HomeActivity extends AppCompatActivity implements HomeFragment.HomeScreenListener,
        AddNotesFragment.AddNoteScreenListener {

    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadHomeFragment();
    }

    private void loadHomeFragment() {
        mHomeFragment = new HomeFragment();
        FragmentHelper.addFragmentWithoutAnimation(this, mHomeFragment, R.id.fragment_container,
                "Home_fragment");
    }

    private void loadAddNotesFragment() {
        FragmentHelper.addFragmentWithoutAnimation(this, new AddNotesFragment(null, false),
                R.id.fragment_container, "AddNote_fragment");
    }

    private void loadNoteDetailsFragment(Note note) {
        FragmentHelper.addFragmentWithoutAnimation(this, new AddNotesFragment(note, true),
                R.id.fragment_container, "AddNote_fragment");
    }

    @Override
    public void onAddBtnClick() {
        loadAddNotesFragment();
    }

    @Override
    public void openNoteDetailsScreen(Note note) {
        loadNoteDetailsFragment(note);
    }

    @Override
    public void onSaveBtnClick(Note note) {
        FragmentHelper.popBackStackImmediate(this);
        mHomeFragment.saveNotes(note);
    }

    @Override
    public void onUpdateNote(Note note) {
        FragmentHelper.popBackStackImmediate(this);
        mHomeFragment.updateNotes(note);
    }
}
