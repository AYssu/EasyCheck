package com.easyverify.springboot;

import com.easyverify.springboot.vo.UserLoginVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FastJson2Tests {

    @Test
    public void test_for_issue() throws Exception {
        UserLoginVO user = new UserLoginVO();
        user.setUserPassword("123456");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
    }

}

