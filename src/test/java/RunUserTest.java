import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.mapper.JedisClient;
import com.running.business.pojo.RunUser;
import com.running.business.service.RunUserService;
import com.running.business.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class RunUserTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private JedisClient jedisClient;
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
		user.setUserphone("111113");
		user.setPassword("123456");
		user.setAddTime(new Date());
		runUserService.saveUser(user);
	}

	@Test
	public void getUser() {
		BaseResult result = runUserService.getRunUser(5);
		System.out.println(result.getCode());
		RunUser user = (RunUser) result.getData();
		System.out.println(user.getUserphone());
		System.out.println(user.getPassword());
		System.out.println(user.getAddTime());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUser(){
		/*int currpage = 1;
		PageHelper.startPage(currpage, 10);
		PageInfo<UserVO> result = runUserService.pageAllRunUser(1, 20, "desc");
		List<UserVO> list = result.getList();
		PageInfo<RunUser> page = new PageInfo<RunUser>(list);
		System.out.println(page.getPages());
		System.out.println(page.getTotal());
		System.out.println(page.getNextPage());
		for (RunUser user : page.getList()) {
			System.out.println(user.getUserphone());
			System.out.println(user.getPassword());
		}*/
	}

	@Test
	public void delUser() {
		BaseResult result = runUserService.deleteUser(2);
		System.out.println(result.getCode());
	}

	@Test
	public void updateUser() {
		BaseResult result = runUserService.getRunUser(4);
		RunUser user = (RunUser) result.getData();
		user.setPassword("1234567");
		result = runUserService.updateUser(user);
		System.out.println(result.getCode());
	}

	@Test
	public void getToken() {
		String token = "REDIS_USER_SESSION:RUedd7e80f-8d62-48d7-be4f-0889b0cfd565";
		String UserLoginSessionStr = jedisClient.get(token);
		System.out.println(UserLoginSessionStr);
		if (UserLoginSessionStr == null || UserLoginSessionStr.equals("")) {
            System.out.println("token已失效");
        } else {
            System.out.println("允许登录");
        }
    }

    @Test
    public void tets() throws Exception {
		Long currentTime = System.currentTimeMillis();
		String f = "2018-03-30 17:33:36";
		Date date ;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(f);
		System.out.println("date : " + date.toString());
		System.out.println("diff : " + (currentTime - date.getTime()));
		Long diffTime = (currentTime - date.getTime()) / (1000 * 60 * 60 * 24);
		if (diffTime > 7) {
			System.out.println(diffTime + " 大于7天");
		}
		System.out.println(diffTime);
	}

	@Test
	public void test() throws Exception {
		Double a = 2.56;
		System.out.println(new Double(a+0.5).intValue());
	}

}
