package labshopcompensationme.domain;

import labshopcompensationme.domain.OrderPlaced;
import labshopcompensationme.domain.OrderCancelled;
import labshopcompensationme.OrderApplication;
import javax.persistence.*;
import labshopcompensationme.OrderApplication;
import labshopcompensationme.domain.OrderPlaced;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String productId;
    
    
    
    
    
    private Integer qty;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private Double amount;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String address;

    @PostPersist
    public void onPostPersist(){


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
        // Get request from Inventory<<<<<<< HEAD:order/src/main/java/labshopcompensation/domain/Order.java
        labshopcompensationme.external.Inventory inventory =
           OrderApplication.applicationContext.getBean(labshopcompensationme.external.InventoryService.class)
           .getInventory(Long.valueOf(getProductId()));

        if(inventory.getStock() < getQty())
            throw new RuntimeException("Out of stock!");
    }
    @PreRemove
    public void onPreRemove(){


        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
