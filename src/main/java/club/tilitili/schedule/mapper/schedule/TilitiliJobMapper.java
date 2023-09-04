package club.tilitili.schedule.mapper.schedule;

import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.mapper.schedule.automapper.TilitiliJobAutoMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface TilitiliJobMapper extends TilitiliJobAutoMapper {
    TilitiliJob getTilitiliJobByName(String name);

    @Update("update tilitili_job set status = #{status} where name = #{name}")
    void updateTilitiliJobByName(String name, Integer status);
}
