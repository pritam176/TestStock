package com.mmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmt.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	@Query("SELECT ue FROM UserEntity ue where ue.username = :username ")
	UserEntity findByUserName(@Param("username") String username);

}
