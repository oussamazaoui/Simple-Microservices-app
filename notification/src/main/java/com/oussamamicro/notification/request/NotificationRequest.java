package com.oussamamicro.notification.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NotificationRequest {
    public NotificationRequest() {
    }
    Integer toCustomerId;
    String toCustomerName;
    String message;
}
