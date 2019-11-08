package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;
import com.lambdaschool.javatodos.repositories.RoleRepository;
import com.lambdaschool.javatodos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Role> findAllRoles() {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    // TODO
    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role findRoleById(long id) {
        return rolerepos.findById(id).orElseThrow(() ->
                new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Role save(Role role) {

        return rolerepos.save(role);
    }

    @Override
    public Role update(long id, Role role) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (findRoleById(id) != null) {
            rolerepos.deleteById(id);
        }
    }
}
