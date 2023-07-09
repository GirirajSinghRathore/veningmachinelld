package org.example.model;


import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Getter
@Setter
@Builder
public class VendingMachine {
    private List<Slot> slots;
    private VendingMachineStatus vendingMachineStatus;
    private Map<String,ProuctQuantity> slotProuctQuantityMap;
    private Screen screen;
    private List<Order> orderList;
    private Order currentOrder;
    private CashModule cashModule;
    private Dispenser dispenser;
    public Order getOrder() throws Exception {
        String slotId = screen.getText();
        Slot slot =  getSlot(slotId);
        if(slot==null){
            throw new Exception("Invalid SLot");
        }
        if(slot.getSlotStatus().equals(SlotStatus.EMPTY)||slot.getSlotStatus().equals(SlotStatus.IN_ERROR)){
            throw new Exception("Slot unavailable");
        }
        Order order = new Order();
        slot.setSlotStatus(SlotStatus.BLOCKED);
        order.setSlot(slot);
        order.setProduct(slotProuctQuantityMap.get(slotId).getProduct());
        order.setPrice(slotProuctQuantityMap.get(slotId).getProduct().getPrice());
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderList.add(order);
        this.currentOrder=order;
        vendingMachineStatus=VendingMachineStatus.IN_USE;
        return order;

    }
    private Slot getSlot(String slotId){
        for (Slot slot : slots){
            if(slot.getSlotId().equals(slotId)){
                return  slot;
            }
        }
        return null;
    }
    public void acceptCash(List<Note> noteList) throws Exception {
         cashModule.acceptCash(noteList);
         int amountReceived = cashModule.countNotes();

         if(amountReceived!=currentOrder.getPrice()){
             throw new Exception("Intsert Exact Same Amount");
         }
         int currentQu = slotProuctQuantityMap.get(currentOrder.getSlot().getSlotId()).getQuantity();
         slotProuctQuantityMap.get(currentOrder.getSlot().getSlotId()).setQuantity(currentQu-1);
         if(currentQu==1){
             currentOrder.getSlot().setSlotStatus(SlotStatus.EMPTY);
         }
        dispenser.dispenseProduct();
        dispenser.setDispenserStatus(DispenserStatus.OPEN);
    }


}
