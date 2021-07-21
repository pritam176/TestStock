/**
 * 
 */
package com.mmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmt.Entity.GSTSlabEntity;
import com.mmt.Entity.ItemTransactionEntity;

/**
 * @author pkumar
 *
 */
public interface GstSlabRepository extends JpaRepository<GSTSlabEntity, Long> {
	
	@Query("SELECT ite FROM  ItemTransactionEntity ite "
			+ "where "
			+ "  ite.invoice_date >= :startdate AND ite.invoice_date <= :enddate and ite.gSTSlabEntity.slab_id = :gstid")
	public List<ItemTransactionEntity> getGSTDataBySlabIdAndDate(@Param("gstid") long gstid, 
			@Param("startdate") Date startdate, @Param("enddate") Date enddate);
	
	List<GSTSlabEntity> findByname(String lastname);
	List<GSTSlabEntity> findBytaxrate(String taxrate);

}
