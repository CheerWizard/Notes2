package com.example.jeremy.customnotes.utils.resolvers;

import com.example.jeremy.customnotes.constants.SortType;
import com.example.jeremy.customnotes.models.Note;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class NoteResolver {
    //returns ordered list by appropriate types
    public static List<Note> sort(final List<Note> list, SortType sortType) {
        switch (sortType) {
            //sort by note date
            case BY_DATE:
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
                break;
            //sort by note category
            case BY_CATEGORY:
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getCategory().compareTo(o2.getCategory());
                    }
                });
                break;
            //sort by note description
            case BY_DESCRIPTION:
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getDescription().compareTo(o2.getDescription());
                    }
                });
                break;
        }
        return list;
    }
}
