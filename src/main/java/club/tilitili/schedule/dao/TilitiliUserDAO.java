package club.tilitili.schedule.dao;

import club.tilitili.schedule.dao.mapper.TilitiliJobMapper;
import club.tilitili.schedule.entity.TilitiliUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TilitiliUserDAO extends TilitiliJobMapper {

    @Select("select * from tilitili_user where user_name = #{userName} and status = 0")
    TilitiliUser getByName(String userName);
}
