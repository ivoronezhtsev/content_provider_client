package ru.voronezhtsev.contentprovider.client.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.voronezhtsev.contentprovider.client.R;

public class NotesListFragment extends Fragment implements NotesActions {
    public static final String TAG = "NotesListFragment";
    private EditNoteFragment mEditNoteFragment;
    private NotesRepository mNotesRepository;
    private FragmentManager mFragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNotesRepository = (NotesRepository) context;
            mFragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Activity should implements NotesRepository interface"
                    + "and extends FragmentsActivity Class", e);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter notesAdapter = new NotesAdapter(mNotesRepository.getNotes(),
                NotesListFragment.this,
                Float.parseFloat(mNotesRepository.getTextSize()),
                Color.parseColor(mNotesRepository.getTextColor()));
        recyclerView.setAdapter(notesAdapter);
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            mEditNoteFragment = new EditNoteFragment();
            mFragmentManager.beginTransaction()
                    .replace(R.id.container, mEditNoteFragment, EditNoteFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void update(long noteId) {
        if (mEditNoteFragment == null) {
            mEditNoteFragment = EditNoteFragment.newInstance(noteId);
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong(EditNoteFragment.KEY_ID, noteId);
            mEditNoteFragment.setArguments(bundle);
        }

        mFragmentManager.beginTransaction()
                .replace(R.id.container, mEditNoteFragment, EditNoteFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}

