package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Slot {
    private String slotId;
    private int capacity;
    private SlotStatus slotStatus;
}
