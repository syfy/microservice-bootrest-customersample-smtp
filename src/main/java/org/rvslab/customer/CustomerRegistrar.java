package org.rvslab.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component 
@Lazy
public class CustomerRegistrar {

	 CustomerRepository customerRespository;
	  Sender sender;
	  
	  @Autowired
	  CustomerRegistrar(CustomerRepository customerRespository, Sender sender){
	    this.customerRespository = customerRespository;
	    this.sender = sender;
	  }
	  
	  Customer register(Customer customer){
		 System.out.println("registering");
	    Optional<Customer> existingCustomer = customerRespository.findByName(customer.getName());
	    if (existingCustomer.isPresent()){
	      throw new RuntimeException("is already exists");
	    } else {
	      customerRespository.save(customer); 
	      sender.send(customer.getEmail());
	    } 
	    return customer;
	  }
}