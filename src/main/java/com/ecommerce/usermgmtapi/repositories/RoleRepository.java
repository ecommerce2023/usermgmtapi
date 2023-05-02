package com.ecommerce.usermgmtapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermgmtapi.models.ERole;
import com.ecommerce.usermgmtapi.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(ERole name);
}
