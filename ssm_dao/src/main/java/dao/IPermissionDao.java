package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id=(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionByRoleId(String id);

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
