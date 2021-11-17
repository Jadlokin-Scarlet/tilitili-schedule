package club.tilitili.schedule.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import club.tilitili.schedule.entity.query.TilitiliLogQuery;
import club.tilitili.schedule.entity.TilitiliLog;

public interface TilitiliLogMapper {
	Long addTilitiliLogSelective(TilitiliLog tilitiliLog);
	void updateTilitiliLogSelective(TilitiliLog tilitiliLog);
	int countTilitiliLogByCondition (TilitiliLogQuery query);
	List<TilitiliLog> getTilitiliLogByCondition (TilitiliLogQuery query);
	TilitiliLog getTilitiliLogById (@Param("id") Long id);
}