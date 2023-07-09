package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dispenser {
    private DispenserStatus dispenserStatus = DispenserStatus.OPEN;
    void dispenseProduct(){
        dispenserStatus=DispenserStatus.DISPENSING;
        System.out.println("Dispensing Product");
    }
}
