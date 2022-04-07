package com.kanto.ecommerce.dao;

import com.kanto.ecommerce.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String usename);
}
