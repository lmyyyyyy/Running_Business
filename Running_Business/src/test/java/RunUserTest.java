import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUser;
import com.running.business.service.RunUserService;


public class RunUserTest {
	static ApplicationContext context = null;
	static RunUserService runUserService;
	static {
		context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		runUserService = (RunUserService) context.getBean("runUserServiceImpl");
	}
	
	/*@Test
	public void login() {
		BaseResult<Object> result = runUserService.login("123456", "1234561");
		System.out.println(result.getCode());
		if (result.getCode().equals("0")) {
			RunUser user = (RunUser) result.getData();
			System.out.println(user.getUserUsername());
			System.out.println(user.getUserPassword());
		} else {
			System.out.println(result.getError().getErrorMsg());
			System.out.println(result.getError().getErrorCode());
		}
	}*/
	
	@Test
	public void addUser() {
		RunUser user = new RunUser();
		user.setUserUsername("111111");
		user.setUserPassword("123456");
		user.setUserDate(new Date());
		runUserService.addUser(user);
	}
	
	@Test
	public void getUser() {
		BaseResult<Object> result = runUserService.getRunUser(1);
		System.out.println(result.getCode());
		RunUser user = (RunUser) result.getData();
		System.out.println(user.getUserUsername());
		System.out.println(user.getUserPassword());
		System.out.println(user.getUserDate());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllUser(){
		int currpage = 1;
		PageHelper.startPage(currpage, 10);
		BaseResult<Object> result = runUserService.getAllRunUser();
		List<RunUser> list = (List<RunUser>) result.getData();
		PageInfo<RunUser> page = new PageInfo<RunUser>(list);
		System.out.println(page.getPages());
		System.out.println(page.getTotal());
		System.out.println(page.getNextPage());
		for (RunUser user : page.getList()) {
			System.out.println(user.getUserUsername());
			System.out.println(user.getUserPassword());
		}
	}
	
	@Test
	public void delUser() {
		BaseResult<Object> result = runUserService.delUser(1);
		System.out.println(result.getCode());
	}
	
	@Test
	public void updateUser() {
		BaseResult<Object> result = runUserService.getRunUser(3);
		RunUser user = (RunUser) result.getData();
		user.setUserPassword("1234567");
		result = runUserService.updateUser(user);
		System.out.println(result.getCode());
	}
}
