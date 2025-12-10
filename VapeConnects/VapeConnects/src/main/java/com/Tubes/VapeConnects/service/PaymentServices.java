package com.Tubes.VapeConnects.service;

import com.Tubes.VapeConnects.model.Order;
import com.Tubes.VapeConnects.model.OrderItem;
import com.midtrans.httpclient.SnapApi;
import com.midtrans.httpclient.error.MidtransError;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServices{
    public String createSnapToken(Order order) throws MidtransError {

        Map<String, Object> params = new HashMap<>();

        // Transaction Details
        Map<String, Object> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", "ORDER-" + order.getId());
        transactionDetails.put("gross_amount", order.getTotal());
        params.put("transaction_details", transactionDetails);

        // Customer Details
        Map<String, Object> customer = new HashMap<>();
        customer.put("first_name", order.getShippingName());
        customer.put("email", order.getShippingEmail());
        customer.put("phone", order.getShippingPhone());
        params.put("customer_details", customer);

        // Item Details
        List<Map<String, Object>> itemDetails = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            Map<String, Object> itemData = new HashMap<>();
            itemData.put("id", item.getProduct().getId());
            itemData.put("price", item.getPrice());
            itemData.put("quantity", item.getQuantity());
            itemData.put("name", item.getProduct().getName());
            itemDetails.add(itemData);
        }
        params.put("item_details", itemDetails);

        // Create Snap Token
        return SnapApi.createTransactionToken(params);
    }
}
