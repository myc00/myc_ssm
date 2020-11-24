package service;

import domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page,int size) throws Exception;

    void save(UserInfo userinfo,String roleId) throws Exception;

    UserInfo findById(String id) throws Exception;
}
