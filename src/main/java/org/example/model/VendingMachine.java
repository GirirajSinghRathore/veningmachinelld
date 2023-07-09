package org.example.model;


import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Getter

@Builder
public class VendingMachine {
    private List<Slot> slots;
    private VendingMachineStatus vendingMachineStatus;
    private Map<String,ProuctQuantity> slotProuctQuantityMap;
    private Screen screen;
    private List<Order> orderList;
    private Order currentOrder;
    private CashModule cashModule;

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
        order.setSlot(slot);
        order.setProduct(slotProuctQuantityMap.get(slotId).getProduct());
        order.setPrice(slotProuctQuantityMap.get(slotId).getProduct().getPrice());
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderList.add(order);
        currentOrder=order;
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


}
