package club.tilitili.schedule.dao;

import club.tilitili.schedule.dao.mapper.TilitiliJobMapper;
import club.tilitili.schedule.entity.TilitiliJob;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface TilitiliJobDAO extends TilitiliJobMapper {
    TilitiliJob getTilitiliJobByName(String name);

    @Update("update tilitili_job set status = #{status} where name = #{name}")
    void updateTilitiliJobByName(String name, Integer status);

}
