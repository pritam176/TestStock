/**
 * 
 */
package com.mmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.SupplierEntity;

/**
 * @author pkumar
 *
 */
@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{
	
	List<SupplierEntity> findBysuppliername(String name);

}
