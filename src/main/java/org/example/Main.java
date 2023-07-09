package org.example;

import com.sun.java.accessibility.util.AccessibilityListenerList;
import org.example.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Slot> slots = new ArrayList<>(6);
        slots.add(new Slot("A1",10, SlotStatus.FULL));
        slots.add(new Slot("A2",10, SlotStatus.FULL));
        slots.add(new Slot("A3",10, SlotStatus.EMPTY));

        slots.add(new Slot("B1",10, SlotStatus.EMPTY));
        slots.add(new Slot("B2",10, SlotStatus.EMPTY));
        slots.add(new Slot("B3",10, SlotStatus.EMPTY));

        Product p1 = new Product("Lays",10);
        Product p2 = new Product("Coke",20);


        Map<String,ProuctQuantity> slotProuctQuantityMap=new HashMap<>();
        slotProuctQuantityMap.put("A1",new ProuctQuantity(p1,10));
        slotProuctQuantityMap.put("A2",new ProuctQuantity(p2,10));
        VendingMachine vendingMachine = VendingMachine.builder()
                .vendingMachineStatus(VendingMachineStatus.FREE)
                .slots(slots)
                .slotProuctQuantityMap(slotProuctQuantityMap)
                .orderList(new ArrayList<>())
                .screen(new Screen())
                .build();
        //----------

        try {
            //Making Order
            vendingMachine.getScreen().setText("A1");
            Order order = vendingMachine.getOrder();
            System.out.println(order.getPrice());
            //Making Payment
            List<Note> notes = new ArrayList<>();
            notes.add(new Note(10));

            vendingMachine.getCashModule().acceptCash(notes);


        }catch (Exception e){
            System.out.println(e);

        }





    }
}