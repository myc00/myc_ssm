package service.impl;

import com.github.pagehelper.PageHelper;
import dao.IUserDao;
import domain.Role;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userDao.findByUsername(username);
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,
                true,true,true,
                getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        List<UserInfo> userInfos=userDao.findAll();
        return userInfos;
    }

    @Override
    public void save(UserInfo userinfo,String orderid) throws Exception {
        userinfo.setPassword(bCryptPasswordEncoder.encode(userinfo.getPassword()));
        userDao.save(userinfo);
        userDao.insertUser_Role(orderid);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        UserInfo userInfo=userDao.findById(id);
        return userInfo;
    }
}
