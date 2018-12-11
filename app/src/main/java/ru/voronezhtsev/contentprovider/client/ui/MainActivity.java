package ru.voronezhtsev.contentprovider.client.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

import ru.voronezhtsev.contentprovider.client.R;
import ru.voronezhtsev.contentprovider.client.data.Note;
import ru.voronezhtsev.contentprovider.client.data.NotesDAO;
import ru.voronezhtsev.contentprovider.client.data.PreferencesDAO;

public class MainActivity extends AppCompatActivity implements NotesRepository{

    private PreferencesDAO mPreferencesDAO = new PreferencesDAO(this);
    private NotesDAO mNotesDAO = new NotesDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            initPrefs();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new NotesListFragment(), NotesListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.settings).setOnMenuItemClickListener(item -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new PrefsFragment(), PrefsFragment.TAG)
                    .addToBackStack(null)
                    .commit();
            return true;
        });
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initPrefs() {
        if(mPreferencesDAO.getTextSize("").isEmpty()) {
            mPreferencesDAO.saveTextSize(PreferencesDAO.DEFAULT_TEXT_SIZE);
        }
        if(mPreferencesDAO.getTextColor("").isEmpty()) {
            mPreferencesDAO.saveTextColor(PreferencesDAO.DEFAULT_TEXT_COLOR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextSize() {
        return mPreferencesDAO.getTextSize(PreferencesDAO.DEFAULT_TEXT_SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextColor() {
        return mPreferencesDAO.getTextColor(PreferencesDAO.DEFAULT_TEXT_COLOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTextSize(String size) {
        mPreferencesDAO.saveTextSize(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTextColor(String color) {
        mPreferencesDAO.saveTextColor(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Note> getNotes() {
        return mNotesDAO.getNotes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Note getNote(long id) {
        return mNotesDAO.getNote(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Note note) {
        mNotesDAO.insert(note);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Note note) {
        mNotesDAO.update(note);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        mNotesDAO.delete(id);
        onBackPressed();
    }
}
