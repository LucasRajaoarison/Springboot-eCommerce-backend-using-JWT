package com.kanto.ecommerce.service;

import com.kanto.ecommerce.entity.AppRole;
import com.kanto.ecommerce.entity.AppUser;

public interface AccountService {

    public AppUser saveUser(AppUser user) ;
    public AppRole saveRole(AppRole role)  ;
    public void addRoleToUser(String username, String roleName);
    public AppUser findUserByUsername(String username) ;
}
