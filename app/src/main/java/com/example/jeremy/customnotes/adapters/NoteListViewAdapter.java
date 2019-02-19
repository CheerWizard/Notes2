package com.example.jeremy.customnotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.business_logic.data.Note;
import com.example.jeremy.customnotes.ui.CustomNoteApplication;
import com.example.jeremy.customnotes.utils.cache.NoteCategoryCache;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteListViewAdapter extends BaseAdapter {

    public interface NoteListEventListener {
        void onDeleteBtn(int position);
        void onUpdateBtn(int position);
    }

    private List<Note> list;
    private NoteListEventListener noteListEventListener;
    private LayoutInflater layoutInflater;
    private NoteViewHolder noteViewHolder;

    @Inject
    public NoteListViewAdapter() {
        layoutInflater = LayoutInflater.from(CustomNoteApplication.getInstance());
        list = new ArrayList<>();
    }

    public void addListener(NoteListEventListener noteListEventListener) {
        this.noteListEventListener = noteListEventListener;
    }

    public void onUpdateList(List<Note> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.note_view_holder_layout , parent , false);
            noteViewHolder = new NoteViewHolder(convertView);
            convertView.setTag(noteViewHolder);
        }
        else noteViewHolder = (NoteViewHolder) convertView.getTag();

        Note note = list.get(position);
        noteViewHolder.dateTextView.setText(note.getDate());
        noteViewHolder.categoryAutoCompleteTextView.setText(note.getCategory());
        noteViewHolder.descriptionEditText.setText(note.getDescription());
        if (noteViewHolder.deleteButton != null) noteViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteListEventListener != null) noteListEventListener.onDeleteBtn(position);
            }
        });
        if (noteViewHolder.updateButton != null) noteViewHolder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteListEventListener != null) noteListEventListener.onUpdateBtn(position);
            }
        });
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CustomNoteApplication.getInstance() , android.R.layout.simple_list_item_1 , NoteCategoryCache.getNoteCategoryArray());
        noteViewHolder.categoryAutoCompleteTextView.setAdapter(arrayAdapter);

        return convertView;
    }

    public EditText getDescriptionEditText() {
        return noteViewHolder.descriptionEditText;
    }

    public AutoCompleteTextView getCategotyAutoCompleteTextView() {
        return noteViewHolder.categoryAutoCompleteTextView;
    }

    private class NoteViewHolder {

        private TextView dateTextView;
        private Button updateButton , deleteButton;
        private AutoCompleteTextView categoryAutoCompleteTextView;
        private EditText descriptionEditText;

        private NoteViewHolder(View view) {
            initViews(view);
        }

        private void initViews(View view) {
            descriptionEditText = view.findViewById(R.id.description_edit_text);
            categoryAutoCompleteTextView = view.findViewById(R.id.category_auto_complete_text_view);
            deleteButton = view.findViewById(R.id.delete_btn);
            updateButton = view.findViewById(R.id.update_btn);
            dateTextView = view.findViewById(R.id.date_text_view);
            final TextView categoriesTextView = view.findViewById(R.id.categories_text_view);
        }
    }
}
