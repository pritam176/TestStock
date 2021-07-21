/**
 * 
 */
package com.mmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.ItemTransactionEntity;

/**
 * @author pkumar
 *
 */
@Repository
public interface ItemTransactionRepository extends JpaRepository<ItemTransactionEntity, Long> {

}
