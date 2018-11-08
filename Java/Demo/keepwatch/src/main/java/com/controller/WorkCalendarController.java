package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.WorkCalendar;
import com.service.WorkCalendarService;
import com.service.result.ResponseModel;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workCalendar")
public class WorkCalendarController {

    @Autowired
    private WorkCalendarService workCalendarService;

    @ResponseBody
    @GetMapping("/getWorkCalendarList")
    public ResponseModel getWorkCalendarList(String userName, String realName) {
        return workCalendarService.getWorkCalendarList(userName, realName);
    }

    @ResponseBody
    @PostMapping("/addWorkCalendar")
    public ResponseModel addWorkCalendar(@RequestBody WorkCalendar model) {
        return workCalendarService.addWorkCalendar(model);
    }

    @ResponseBody
    @PostMapping("/updateWorkCalendar")
    public ResponseModel updateWorkCalendar(@RequestBody WorkCalendar model) {
        return workCalendarService.updateWorkCalendar(model);
    }

    @ResponseBody
    @PostMapping("/delWorkCalendar")
    public ResponseModel delWorkCalendar(int id) {
        return workCalendarService.delWorkCalendar(id);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss"), true));
    }
}