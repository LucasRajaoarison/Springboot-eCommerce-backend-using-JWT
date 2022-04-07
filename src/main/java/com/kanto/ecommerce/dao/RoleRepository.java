package com.kanto.ecommerce.dao;

import com.kanto.ecommerce.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRoleName(String roleName) ;
}
