package vn.khangktn.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.khangktn.laptopshop.domain.Role;
import vn.khangktn.laptopshop.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired RoleRepository roleRepository;

    public Role getRolebyName(String roleName){
        return roleRepository.findByName(roleName);
    }
}
