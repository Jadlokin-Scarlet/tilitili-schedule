package club.tilitili.schedule.mapper.mysql.automapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import club.tilitili.schedule.entity.query.TilitiliUserQuery;
import club.tilitili.schedule.entity.TilitiliUser;

public interface TilitiliUserAutoMapper {
	int addTilitiliUserSelective(TilitiliUser tilitiliUser);
	int updateTilitiliUserSelective(TilitiliUser tilitiliUser);
	int countTilitiliUserByCondition (TilitiliUserQuery query);
	List<TilitiliUser> getTilitiliUserByCondition (TilitiliUserQuery query);
	TilitiliUser getTilitiliUserById (@Param("id") Long id);
	int deleteTilitiliUserByPrimary (@Param("id") Long id);
}