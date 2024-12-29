package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
public class YummyOrderRequest {

    Customer customer;
    List<OrderItem> orderItems;
    DeliveryDetails deliveryDetails;
    PaymentDetails paymentDetails;
    String customerComment;

    @With
    @Value
    @Builder
    public static class Customer {
        String name;
        String surname;
        String phone;
        String email;
        Address address;
    }

    @With
    @Value
    @Builder
    public static class Address {
        String country;
        String city;
        String postalCode;
        String street;
    }

    @With
    @Value
    @Builder
    public static class OrderItem {
        String dishName;
        Integer quantity;
        List<String> ingredients; // List of ingredients or customizations
        String specialInstructions;
    }


    @With
    @Value
    @Builder
    public static class DeliveryDetails {
        String deliveryMethod; // e.g., "HOME_DELIVERY", "PICK_UP"
        String estimatedDeliveryTime; // e.g., "45 minutes"
    }


    @With
    @Value
    @Builder
    public static class PaymentDetails {
        String paymentMethod; // e.g., "CREDIT_CARD", "CASH", "ONLINE_PAYMENT"
        String transactionId; // Optional, for online payments
    }
}
