package controller;


import com.github.pagehelper.PageInfo;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//
//        List<UserInfo> userInfos=userService.findAll();
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("userList",userInfos);
//        mv.setViewName("user-list");
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true) int page,@RequestParam(name = "size",defaultValue = "4",required = true) int size) throws Exception {

        List<UserInfo> userInfos=userService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(userInfos);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }

    /**
     * 这里要将新建用户的权限也获取到，插入到users_role表中，
     * 不然新建的用户会因为无法通过级联查询查到自己的权限而无法登录
     * @param userinfo
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userinfo,String permission) throws Exception {
        userService.save(userinfo,permission);
        return "redirect:findAll.do";
    }

    /**
     * 用户详情查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        UserInfo userInfo=userService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
