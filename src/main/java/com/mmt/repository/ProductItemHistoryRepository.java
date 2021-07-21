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

import com.mmt.Entity.ProductItemHistoryEntity;

/**
 * @author pkumar
 *
 */
@Repository
public interface ProductItemHistoryRepository extends JpaRepository<ProductItemHistoryEntity, Long> {

	@Query("SELECT pme FROM  ProductItemHistoryEntity pme "
			+ "where pme.productItemEntities.productitementity_id = :itemid and pme.updateReason like :reason "
			+ "and pme.addedon>= :startdate AND pme.addedon<= :enddate")
	public List<ProductItemHistoryEntity> getProductData(@Param("itemid") long itemid, @Param("reason") String reason,
			@Param("startdate") Date startdate, @Param("enddate") Date enddate);

}
