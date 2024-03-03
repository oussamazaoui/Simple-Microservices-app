package com.oussamamicro.customer.service;

import com.oussamamicro.RabbitMq.RabbitMQMessageProducer;
import com.oussamamicro.customer.entity.Customer;
import com.oussamamicro.customer.repositry.CustomerRepository;
import com.oussamamicro.customer.request.CustomerRegistrationRequest;
import com.oussamamicro.fraud.response.FraudCheckResponse;
import com.oussamamicro.notification.request.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        //This ensures that the entire operation (including the save and flush) is atomic
        // and either succeeds entirely or
        // fails entirely, adhering to the principles of ACID transactions.

        FraudCheckResponse fraudCheckResponse= restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

//        FraudCheckResponse fraudCheckResponse =
//                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.response()) {
            throw new IllegalStateException("fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, IM OUSSAMA...",
                        customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );



    }
}
