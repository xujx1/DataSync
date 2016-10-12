import com.xujinxin.datasync.bean.User;
import com.xujinxin.datasync.enums.ResponseType;
import com.xujinxin.datasync.service.UserService;
import com.xujinxin.datasync.util.redis.RedisOperationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class CommonTest extends SpringTestCase {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperationService redisOperationService;

    @Before
    public void before() {
        System.out.println("=========================================================");
    }

    @After
    public void after() {
        System.out.println("=========================================================");
    }

    @Test
    public void test() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void selectUserById() {
        System.out.println(userService.selectUserById(1));
    }

    @Test
    public void selectAll() {
        System.out.println(userService.selectAll());
    }

    @Test
    public void selectAllByJdbcTemplate() {
        System.out.println(userService.selectAllByJdbcTemplate());
    }

    @Test
    public void responseType() {
        System.out.println(ResponseType.FAIL.getMsg());
    }

    @Test
    public void RedisTest() {
        System.out.println(redisOperationService.ping());
        User user = new User();
        user.setUsername("2312");
        user.setPassword("asd");
        redisOperationService.setCache("test", user, 300, TimeUnit.SECONDS);
        System.out.println(redisOperationService.getCache("test", User.class));
        redisOperationService.clear();
    }
}
