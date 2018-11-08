package duty.service;
import java.util.List;
import duty.entity.WorkCalendar;

public interface WorkCalendarService {
	
	public WorkCalendar getWorkCalendarById(int id);

	public List<WorkCalendar> getWorkCalendarList();
	
	public Boolean insertWorkCalendar(WorkCalendar model);
	
	public Boolean delWorkCalendar(int id);
	
	public Boolean updateWorkCalendar(WorkCalendar model);
}
