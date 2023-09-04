package club.tilitili.schedule.mapper.schedule.automapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import club.tilitili.schedule.entity.query.TilitiliLogQuery;
import club.tilitili.schedule.entity.TilitiliLog;

public interface TilitiliLogAutoMapper {
	int addTilitiliLogSelective(TilitiliLog tilitiliLog);
	int updateTilitiliLogSelective(TilitiliLog tilitiliLog);
	int countTilitiliLogByCondition (TilitiliLogQuery query);
	List<TilitiliLog> getTilitiliLogByCondition (TilitiliLogQuery query);
	TilitiliLog getTilitiliLogById (@Param("id") Long id);
	int deleteTilitiliLogByPrimary (@Param("id") Long id);
}