package com.service.impl;

import java.util.Date;
import java.util.List;
import com.model.WorkCalendar;
import com.mapper.WorkCalendarMapper;
import com.service.WorkCalendarService;
import com.service.result.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkCalendarServiceImpl implements WorkCalendarService {

    @Autowired
    private WorkCalendarMapper workCalenderMap;

    @Override
    public ResponseModel getWorkCalendarList(String userName, String realName) {
        ResponseModel result = new ResponseModel();
        List<WorkCalendar> workCalendars = workCalenderMap.getWorkCalendarList(userName, realName);
        if (workCalendars != null && !workCalendars.isEmpty()) {
            // 返回数据报文
            result.setData(workCalendars);
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public ResponseModel addWorkCalendar(WorkCalendar model) {
        ResponseModel result = new ResponseModel();
        if (model == null) {
            result.setErrcode("100003");
            result.setErrmsg("参数异常！！！");
            return result;
        }

        Date date = new Date();
        model.setCreatetime(date);
        model.setUpdatetime(date);
        int subNumber = workCalenderMap.insert(model);
        result.setSuccess(subNumber > 0);
        return result;
    }

    public ResponseModel updateWorkCalendar(WorkCalendar model) {
        ResponseModel result = new ResponseModel();
        if (model == null) {
            result.setErrcode("100003");
            result.setErrmsg("参数异常！！！");
            return result;
        }
        Date date = new Date();
        model.setUpdatetime(date);
        int subNumber = workCalenderMap.updateByKey(model);
        result.setSuccess(subNumber > 0);
        return result;
    }

    public ResponseModel delWorkCalendar(int id) {
        ResponseModel result = new ResponseModel();
        if (id <= 0) {
            result.setErrcode("100003");
            result.setErrmsg("参数异常！！！");
            return result;
        }
        int subNumber = workCalenderMap.deleteByKey(id);
        result.setSuccess(subNumber > 0);
        return result;
    }
}