package com.mapper;

import com.model.WorkCalendar;

public interface WorkCalendarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkCalendar record);

    int insertSelective(WorkCalendar record);

    WorkCalendar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkCalendar record);

    int updateByPrimaryKey(WorkCalendar record);
}