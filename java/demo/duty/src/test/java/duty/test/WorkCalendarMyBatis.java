package duty.test;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duty.entity.WorkCalendar;
import duty.service.WorkCalendarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring.xml", "classpath*:/spring-mybatis.xml" })
public class WorkCalendarMyBatis {

	@Autowired
	private WorkCalendarService workCalendarService;
	
	@Test
	public void getWorkCalendarByIdTest() {
		WorkCalendar work = workCalendarService.getWorkCalendarById(1);
		System.out
				.println("用户Id：" + work.getUserid() + "值班开始时间：" + work.getBegintime() + "值班结束时间：" + work.getEndtime());
	}

	@Test
	public void getWorkCalendarListTest() {
		List<WorkCalendar> workList = workCalendarService.getWorkCalendarList();
		for (WorkCalendar work : workList) {
			System.out.println(
					"用户Id：" + work.getUserid() + "值班开始时间：" + work.getBegintime() + "值班结束时间：" + work.getEndtime());
		}
	}

	@Test
	public void insertWorkCalendarTest() {
		Date date = new Date();
		WorkCalendar model = new WorkCalendar();
		model.setBegintime(date);
		model.setEndtime(date);
		model.setCreatetime(date);
		model.setUserid(2);
		Boolean isSuccess = workCalendarService.insertWorkCalendar(model);
		System.out.println("新增數據結果：" + isSuccess);
	}

	@Test
	public void delWorkCalendarTest() {
		Boolean isSuccess = workCalendarService.delWorkCalendar(3);
		System.out.println("刪除數據結果：" + isSuccess);
	}

	@Test
	public void updateWorkCalendarTest() {
		Date date = new Date();
		WorkCalendar model = new WorkCalendar();
		model.setId(2);
		model.setBegintime(date);
		model.setEndtime(date);
		model.setCreatetime(date);
		model.setUserid(2);
		Boolean isSuccess = workCalendarService.updateWorkCalendar(model);
		System.out.println("修改數據結果：" + isSuccess);
	}
}