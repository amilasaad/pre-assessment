package com.customerinformation.project.controller;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerinformation.project.entity.CustomerInformationEntity;
import com.customerinformation.project.service.CustomerInformationServiceImpl;
import com.customerinformation.project.util.ApplicationConstantUtil;
@Slf4j
@RestController
@RequestMapping("/api")
public class CustomerInformationContoller {

	@Autowired
	private CustomerInformationServiceImpl theCustomerInformationServiceImpl;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CustomerInformationEntity theCustomerInformationEntity) {
		if(theCustomerInformationEntity == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		theCustomerInformationServiceImpl.save(theCustomerInformationEntity);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/update") 
	public ResponseEntity<?> update(@RequestBody CustomerInformationEntity theCustomerInformationEntity, 
			@RequestParam String Id){
		if(theCustomerInformationEntity == null || Id.equals(null)) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		String ServiceStatus = theCustomerInformationServiceImpl.update(Id, theCustomerInformationEntity);
		log.info(" >>>>>>>> {}", ServiceStatus);
		if(ServiceStatus.equals(ApplicationConstantUtil.NOT_FOUND)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/getCustomerById")
	public ResponseEntity<?> getById(@RequestParam String Id) {
		if(Id.equals(null)) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		Optional<CustomerInformationEntity> theCustomerInformationEntity = 
				theCustomerInformationServiceImpl.findCustomerById(Id);
		
		return ResponseEntity.of(theCustomerInformationEntity);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		List<CustomerInformationEntity> theCustomerInformationEntity = 
				theCustomerInformationServiceImpl.findAllCustomer();
		
		return ResponseEntity.ok(theCustomerInformationEntity);
	}
	
	@DeleteMapping("/deleteCustomerById")
	public ResponseEntity<?> deleteCustomerById(@RequestParam String Id) {
		if(Id.equals(null)) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		String ServiceStatus = 
				theCustomerInformationServiceImpl.delete(Id);
		log.info(" >>>>>>>> {}", ServiceStatus);
		if(ServiceStatus.equals(ApplicationConstantUtil.NOT_FOUND)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
