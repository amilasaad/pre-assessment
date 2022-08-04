package com.customerinformation.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerinformation.project.dao.CustormerInformationRepository;
import com.customerinformation.project.entity.CustomerInformationEntity;
import com.customerinformation.project.util.ApplicationConstantUtil;

@Service
public class CustomerInformationServiceImpl implements CustomerInformationService {

	@Autowired
	private CustormerInformationRepository theCustormerInformationRepository;
	
	@Transactional
	@Override
	public void save(CustomerInformationEntity theCustomerInformationEntity) {
		theCustormerInformationRepository.save(theCustomerInformationEntity);
	}

	@Transactional
	@Override
	public String update(String Id, CustomerInformationEntity theCustomerInformationEntity) {
		Optional<CustomerInformationEntity> CustomerData = findCustomerById(Id);
		if(!CustomerData.isPresent()) {
			return ApplicationConstantUtil.NOT_FOUND;
		}
		CustomerInformationEntity oldCustomer = CustomerData.get();
		oldCustomer.setName(theCustomerInformationEntity.getName());
		oldCustomer.setAge(theCustomerInformationEntity.getAge());

		theCustormerInformationRepository.save(oldCustomer);
		
		return ApplicationConstantUtil.OK;
	}

	@Transactional
	@Override
	public Optional<CustomerInformationEntity> findCustomerById(String Id) {
		return theCustormerInformationRepository.findById(Integer.parseInt(Id));
	}
	
	@Transactional
	@Override
	public List<CustomerInformationEntity> findAllCustomer() {
		return theCustormerInformationRepository.findAll();
	}

	@Transactional
	@Override
	public String delete(String Id) {
		Optional<CustomerInformationEntity> theCustomerInformationEntity = findCustomerById(Id);
		if(!theCustomerInformationEntity.isPresent()) {
			return ApplicationConstantUtil.NOT_FOUND;
		}
		
		theCustormerInformationRepository.deleteById(Integer.parseInt(Id));
		
		return ApplicationConstantUtil.OK;
	}

}
