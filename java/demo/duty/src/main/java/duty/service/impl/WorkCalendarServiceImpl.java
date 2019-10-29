package duty.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import duty.dao.WorkCalendarMapper;
import duty.entity.WorkCalendar;
import duty.service.WorkCalendarService;

@Service("workCalendarService")
public class WorkCalendarServiceImpl implements WorkCalendarService {

	@Autowired
	private WorkCalendarMapper workCalendarMapper;

	public WorkCalendar getWorkCalendarById(int id) {
		return workCalendarMapper.selectByPrimaryKey(id);
	}

	public List<WorkCalendar> getWorkCalendarList() {
		return workCalendarMapper.getWorkCalendarList();
	}

	public Boolean insertWorkCalendar(WorkCalendar model) {
		int num = workCalendarMapper.insert(model);
		return num > 0;
	}

	public Boolean delWorkCalendar(int id) {
		int num = workCalendarMapper.deleteByPrimaryKey(id);
		return num > 0;
	}

	public Boolean updateWorkCalendar(WorkCalendar model) {
		int num = workCalendarMapper.updateByPrimaryKey(model);
		return num > 0;
	}
}
