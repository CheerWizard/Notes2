package com.example.jeremy.customnotes.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeremy.customnotes.R;
import com.example.jeremy.customnotes.models.Note;

import java.util.List;

public class NoteListViewAdapter extends BaseAdapter {

    public interface NoteListEventListener {
        void onDeleteBtn(int position);
        void onUpdateBtn(int position);
    }

    private List<Note> list;
    private NoteListEventListener noteListEventListener;
    private LayoutInflater layoutInflater;
    private NoteViewHolder noteViewHolder;

    public NoteListViewAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void addListener(NoteListEventListener noteListEventListener) {
        this.noteListEventListener = noteListEventListener;
    }

    public void onUpdateList(List<Note> newList) {
        this.list = newList;
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
        noteViewHolder.dateTextView.setText(note.getDate().toString());
        noteViewHolder.categoryEditText.setText(note.getCategory());
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
        return convertView;
    }

    public TextView getDateTextView() {
        return noteViewHolder.dateTextView;
    }

    public EditText getDescriptionEditText() {
        return noteViewHolder.descriptionEditText;
    }

    public EditText getCategoryEditText() {
        return noteViewHolder.categoryEditText;
    }

    private class NoteViewHolder {

        private TextView dateTextView;
        private Button updateButton , deleteButton;
        private EditText descriptionEditText , categoryEditText;

        private NoteViewHolder(View view) {
            initViews(view);
        }

        private void initViews(View view) {
            descriptionEditText = view.findViewById(R.id.description_edit_text);
            categoryEditText = view.findViewById(R.id.category_edit_text);
            deleteButton = view.findViewById(R.id.delete_btn);
            updateButton = view.findViewById(R.id.update_btn);
            dateTextView = view.findViewById(R.id.date_text_view);
        }
    }
}
