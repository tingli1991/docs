package duty.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import duty.entity.WorkCalendar;
import duty.service.WorkCalendarService;


@Controller
@RequestMapping("/workCalendar")
public class WorkCalendarController {
	private WorkCalendarService workService;
	public WorkCalendarService getWorkCalendarService() {
		return workService;
	}

	@Autowired
	public void setWorkCalendarService(WorkCalendarService workCalendarService) {
		this.workService = workCalendarService;
	}

	@RequestMapping("/getWorkCalendarById")
	public WorkCalendar getWorkCalendarById(int id) {
		return workService.getWorkCalendarById(id);
	}

	@RequestMapping("/getWorkCalendarList")
	public List<WorkCalendar> getWorkCalendarList() {
		return workService.getWorkCalendarList();
	}

	@RequestMapping("/insertWorkCalendar")
	public Boolean insertWorkCalendar(WorkCalendar model) {
		return workService.insertWorkCalendar(model);
	}

	@RequestMapping("/delWorkCalendar")
	public Boolean delWorkCalendar(int id) {
		return workService.delWorkCalendar(id);
	}

	@RequestMapping("/updateWorkCalendar")
	public Boolean updateWorkCalendar(WorkCalendar model) {
		return workService.updateWorkCalendar(model);
	}
}
