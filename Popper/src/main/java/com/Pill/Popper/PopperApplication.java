package com.Pill.Popper;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Pill.Popper.dao.entity.RoleEntity;
import com.Pill.Popper.dao.repository.RoleRepository;
import com.Pill.Popper.enums.ERole;

@SpringBootApplication

public class PopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopperApplication.class, args);
	}
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	private void createRolesIfNotExist() {
		Optional<RoleEntity> roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
		if(!roleAdmin.isPresent()) {
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setName(ERole.ROLE_ADMIN);
			roleRepository.save(roleEntity);
		}
		
		Optional<RoleEntity> roleUser = roleRepository.findByName(ERole.ROLE_USER);
		if(!roleUser.isPresent()) {
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setName(ERole.ROLE_USER);
			roleRepository.save(roleEntity);
		}
	}

}
