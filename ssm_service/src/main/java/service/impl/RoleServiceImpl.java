package service.impl;

import dao.IRoleDao;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IRoleService;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() {
        List<Role> roles=roleDao.findAll();
        return roles;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
