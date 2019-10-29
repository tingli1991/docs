package com.service;

import com.model.WorkCalendar;
import com.service.result.ResponseModel;

public interface WorkCalendarService {
    public ResponseModel getWorkCalendarList(String userName, String realName);

    public ResponseModel addWorkCalendar(WorkCalendar model);

    public ResponseModel updateWorkCalendar(WorkCalendar model);

    public ResponseModel delWorkCalendar(int id);
}