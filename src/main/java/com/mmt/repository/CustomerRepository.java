
package com.mmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Query("SELECT ce FROM CustomerEntity ce WHERE ce.mobileNo = :mobileNo")
	public CustomerEntity getCustomerByMobileNO(@Param("mobileNo") String mobileNo);

	@Query("SELECT ce FROM CustomerEntity ce WHERE ce.name = :name")
	public CustomerEntity getCustomerByName(@Param("name") String name);
	
	List<CustomerEntity> findByname(String name);

}
