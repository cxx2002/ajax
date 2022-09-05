import com.alibaba.fastjson.JSON;
import org.junit.Test;
import pojo.user;


public class fastJSON {
    @Test
    public void test() {
        user user = new user("001","zhangsan",20);
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);
    }
}
