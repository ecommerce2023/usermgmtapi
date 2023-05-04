package com.ecommerce.usermgmtapi.repositories;

import com.ecommerce.usermgmtapi.models.ERole;
import com.ecommerce.usermgmtapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(ERole name);
}
