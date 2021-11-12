package com.tilitili.schedule.dao;

import com.tilitili.schedule.dao.mapper.TilitiliJobMapper;
import com.tilitili.schedule.entity.TilitiliUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TilitiliUserDAO extends TilitiliJobMapper {

    @Select("select * from tilitili_user where user_name = #{userName} and status = 0")
    TilitiliUser getByName(String userName);
}
