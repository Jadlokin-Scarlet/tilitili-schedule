package com.tilitili.schedule.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.tilitili.schedule.entity.query.TilitiliJobQuery;
import com.tilitili.schedule.entity.TilitiliJob;

public interface TilitiliJobMapper {
	Long addTilitiliJobSelective(TilitiliJob tilitiliJob);
	void updateTilitiliJobSelective(TilitiliJob tilitiliJob);
	int countTilitiliJobByCondition (TilitiliJobQuery query);
	List<TilitiliJob> getTilitiliJobByCondition (TilitiliJobQuery query);
	TilitiliJob getTilitiliJobByName (@Param("name") String name);
	TilitiliJob getTilitiliJobById (@Param("id") Long id);
}