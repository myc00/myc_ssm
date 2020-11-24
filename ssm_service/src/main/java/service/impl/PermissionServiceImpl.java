package service.impl;

import dao.IPermissionDao;
import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IPermissionService;

import java.util.List;


@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        List<Permission> permissions=permissionDao.findAll();
        return permissions;
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
