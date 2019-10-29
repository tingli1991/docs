package com.mapper;

import java.util.List;
import com.model.WorkCalendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkCalendarMapper {

    WorkCalendar selectByKey(Integer id);

    int deleteByKey(Integer id);

    int insert(WorkCalendar record);

    int updateByKey(WorkCalendar record);

    List<WorkCalendar> getWorkCalendarList(@Param("userName") String userName, @Param("realName") String realName);
}