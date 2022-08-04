package com.customerinformation.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerinformation.project.entity.CustomerInformationEntity;

@Repository
public interface CustormerInformationRepository extends JpaRepository<CustomerInformationEntity, Integer> {

}
