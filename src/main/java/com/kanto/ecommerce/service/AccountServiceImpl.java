package com.kanto.ecommerce.service;

import com.kanto.ecommerce.dao.RoleRepository;
import com.kanto.ecommerce.dao.UserRepository;
import com.kanto.ecommerce.entity.AppRole;
import com.kanto.ecommerce.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPWD = bCryptPasswordEncoder.encode(user.getPassword()) ;
        user.setPassword(hashPWD);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        AppRole role = roleRepository.findByRoleName(roleName) ;
        AppUser user = userRepository.findByUsername(username) ;
        user.getRoles().add(role) ;

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
