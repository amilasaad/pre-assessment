package com.customerinformation.project.service;

import java.util.List;
import java.util.Optional;

import com.customerinformation.project.entity.CustomerInformationEntity;
import org.springframework.stereotype.Component;


public interface CustomerInformationService {

	public void save(CustomerInformationEntity theCustomerInformationEntity);
	public String update(String Id, CustomerInformationEntity CustomerInformationEntity);
	public Optional<CustomerInformationEntity> findCustomerById(String Id);
	public List<CustomerInformationEntity> findAllCustomer();
	public String delete(String Id);
	
}
