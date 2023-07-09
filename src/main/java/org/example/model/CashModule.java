package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CashModule {
    private CashModuleStatus cashModuleStatus = CashModuleStatus.FREE;
    private List<Note> noteList;
    private List<Note> validNotes=new ArrayList<>();
    public boolean acceptCash(List<Note> noteList){
        if(cashModuleStatus.equals(CashModuleStatus.BLOCKED)){
            return false;

        }

        for(Note note : noteList){
            boolean valid = false;
            for(Note validNotes: validNotes){
                if(validNotes.getValue()==note.getValue()){
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
    public int countNotes(){
        int n = 0;
        for(Note note:noteList){
            n=n+note.getValue();
        }
        return n;
    }
    public void returnCash(){
        noteList=new ArrayList<>();
    }
    public void saveCash(){
        noteList=new ArrayList<>();
    }
}
