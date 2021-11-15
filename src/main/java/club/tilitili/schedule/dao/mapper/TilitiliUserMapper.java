package club.tilitili.schedule.dao.mapper;

import java.util.List;

import club.tilitili.schedule.entity.query.TilitiliUserQuery;
import org.apache.ibatis.annotations.Param;
import club.tilitili.schedule.entity.TilitiliUser;

public interface TilitiliUserMapper {
	Long addTilitiliUserSelective(TilitiliUser tilitiliUser);
	void updateTilitiliUserSelective(TilitiliUser tilitiliUser);
	int countTilitiliUserByCondition (TilitiliUserQuery query);
	List<TilitiliUser> getTilitiliUserByCondition (TilitiliUserQuery query);
	TilitiliUser getTilitiliUserById (@Param("id") Long id);
}