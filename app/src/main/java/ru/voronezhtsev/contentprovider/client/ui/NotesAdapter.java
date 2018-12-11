package ru.voronezhtsev.contentprovider.client.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.voronezhtsev.contentprovider.client.R;
import ru.voronezhtsev.contentprovider.client.data.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> mNotes;
    private final NotesActions mNotesActions;
    private final float mTextSize;
    private final int mTextColor;

    public NotesAdapter(List<Note> notes, NotesActions notesActions, float textSize, int textColor) {
        mNotes = notes;
        mNotesActions = notesActions;
        mTextSize = textSize;
        mTextColor = textColor;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note, parent, false);

        return new NotesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.mDataTextView.setText(mNotes.get(position).getNote());
        holder.mDataTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        holder.mDataTextView.setTextColor(mTextColor);
        final int pos = position;
        holder.itemView.setOnClickListener(v -> mNotesActions.update(mNotes.get(pos).getId()));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView mDataTextView;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mDataTextView = itemView.findViewById(R.id.note_data_textView);
        }
    }
}
