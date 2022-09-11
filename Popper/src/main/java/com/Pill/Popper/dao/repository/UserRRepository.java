package com.Pill.Popper.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pill.Popper.dao.entity.User;

@Repository
public interface UserRRepository extends JpaRepository<User, Long> {

	List<User> findByCreatedBy(Long id);
}
