package club.tilitili.schedule.mapper.schedule;

import club.tilitili.schedule.entity.TilitiliUser;
import club.tilitili.schedule.mapper.schedule.automapper.TilitiliUserAutoMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TilitiliUserMapper extends TilitiliUserAutoMapper {

    @Select("select * from tilitili_user where user_name = #{userName} and status = 0")
    TilitiliUser getByName(String userName);
}
