package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class CashModule {
    private CashModuleStatus cashModuleStatus;
    private List<Note> noteList;
    private List<Note> validNotes;
    public boolean acceptCash(List<Note> noteList){
        if(cashModuleStatus.equals(CashModuleStatus.BLOCKED)){
            return false;

        }

        for(Note note : noteList){
            boolean valid = false;
            for(Note validNotes: validNotes){
                if(validNotes.equals(note)){
                    valid=true;
                    break;
                }
            }
            if(!valid){
                return false;
            }
        }
        this.noteList=noteList;
        return true;
    }
    int countNotes(){
        int n = 0;
        for(Note note:noteList){
            n=n+note.getValue();
        }
        return n;
    }
    void returnCash(){
        noteList=new ArrayList<>();
    }
    void saveCash(){
        noteList=new ArrayList<>();
    }
}
