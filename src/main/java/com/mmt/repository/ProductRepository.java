/**
 * 
 */
package com.mmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.ProductEntity;

/**
 * @author pkumar
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	List<ProductEntity> findByproductname(String product_Name);

}
