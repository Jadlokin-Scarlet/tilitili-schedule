package com.tilitili.schedule.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.tilitili.schedule.entity.query.TilitiliUserQuery;
import com.tilitili.schedule.entity.TilitiliUser;

public interface TilitiliUserMapper {
	Long addTilitiliUserSelective(TilitiliUser tilitiliUser);
	void updateTilitiliUserSelective(TilitiliUser tilitiliUser);
	int countTilitiliUserByCondition (TilitiliUserQuery query);
	List<TilitiliUser> getTilitiliUserByCondition (TilitiliUserQuery query);
	TilitiliUser getTilitiliUserById (@Param("id") Long id);
}