package com.tilitili.schedule.controller;

import com.tilitili.schedule.entity.BaseModel;
import com.tilitili.schedule.entity.TilitiliUser;
import com.tilitili.schedule.service.TilitiliUserService;
import com.tilitili.schedule.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private final TilitiliUserService tilitiliUserService;

    @Autowired
    public UserController(TilitiliUserService tilitiliUserService) {
        this.tilitiliUserService = tilitiliUserService;
    }

    @GetMapping("/isLogin")
    @ResponseBody
    public BaseModel<?> isLogin(@SessionAttribute(value = "tilitiliUser", required = false) TilitiliUser tilitiliUser) {
        if (tilitiliUser == null) {
            return new BaseModel<>("请重新登陆");
        }else {
            return new BaseModel<>("已登录", true, tilitiliUser);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseModel<?> login(@RequestBody TilitiliUser reqTilitiliUser, HttpSession session) {
        Asserts.notNull(reqTilitiliUser, "参数异常");
        Asserts.notNull(reqTilitiliUser.getUserName(), "请输入用户名");
        Asserts.notNull(reqTilitiliUser.getPassword(), "请输入密码");
        TilitiliUser tilitiliUser = tilitiliUserService.login(reqTilitiliUser.getUserName(), reqTilitiliUser.getPassword());
        Asserts.notNull(tilitiliUser, "账号未获取到");
        session.setAttribute("tilitiliUser", tilitiliUser);
        return new BaseModel<>("登录成功", true, tilitiliUser);
    }

    @PostMapping("/loginOut")
    @ResponseBody
    public BaseModel<?> loginOut(HttpSession session) {
        session.removeAttribute("tilitiliUser");
        return new BaseModel<>("退出登录成功", true);
    }
}
