/**
 * 
 */
package com.mmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.TransactionEntity;

/**
 * @author pkumar
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
	
	@Query("select te from TransactionEntity te where te.invoice_date>= :startdate AND te.invoice_date<= :enddate")
	List<TransactionEntity>  findByInvoice_DateBetween (@Param("startdate")Date startdate, @Param("enddate") Date enddate);

}
