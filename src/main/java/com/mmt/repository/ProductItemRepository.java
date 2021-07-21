package com.mmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.ProductItemEntity;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItemEntity, Long> {
	
	List<ProductItemEntity> findByitemname(String item_Name);

	 

}
