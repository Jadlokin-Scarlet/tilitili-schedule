package club.tilitili.schedule.mapper.schedule.automapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.entity.TilitiliJob;

public interface TilitiliJobAutoMapper {
	int addTilitiliJobSelective(TilitiliJob tilitiliJob);
	int updateTilitiliJobSelective(TilitiliJob tilitiliJob);
	int countTilitiliJobByCondition (TilitiliJobQuery query);
	List<TilitiliJob> getTilitiliJobByCondition (TilitiliJobQuery query);
	TilitiliJob getTilitiliJobByName (@Param("name") String name);
	TilitiliJob getTilitiliJobById (@Param("id") Long id);
	int deleteTilitiliJobByPrimary (@Param("id") Long id);
}