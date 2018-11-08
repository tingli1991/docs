package duty.dao;

import java.util.List;

import duty.entity.WorkCalendar;

public interface WorkCalendarMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(WorkCalendar record);

	int insertSelective(WorkCalendar record);

	WorkCalendar selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WorkCalendar record);

	int updateByPrimaryKey(WorkCalendar record);

	List<WorkCalendar> getWorkCalendarList();
}