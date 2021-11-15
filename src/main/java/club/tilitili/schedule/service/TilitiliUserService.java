package club.tilitili.schedule.service;

import club.tilitili.schedule.dao.TilitiliUserDAO;
import club.tilitili.schedule.entity.TilitiliUser;
import club.tilitili.schedule.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Service
public class TilitiliUserService {

    private final TilitiliUserDAO tilitiliUserDAO;

    @Autowired
    public TilitiliUserService(TilitiliUserDAO tilitiliUserDAO) {
        this.tilitiliUserDAO = tilitiliUserDAO;
    }

    public TilitiliUser login(String userName, String password) {
        TilitiliUser tilitiliUser = tilitiliUserDAO.getByName(userName);
        Asserts.isTrue(tilitiliUser != null, "账号不存在");
        Assert.isTrue(Objects.equals(DigestUtils.md5DigestAsHex(password.getBytes()), tilitiliUser.getPassword()), "密码错误");
        return tilitiliUser;
    }

}
