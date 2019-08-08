package com.amqp.constructor;

import com.zhongfei.notice.client.MessagesClient;
import com.zhongfei.notice.client.impl.MessageClientImpl;
import com.zhongfei.notice.condition.MessageDetailQueryCondition;
import com.zhongfei.notice.condition.MessagesPagingQueryCondition;
import com.zhongfei.notice.domain.BaseDomain;
import com.zhongfei.notice.domain.Result;
import com.zhongfei.notice.request.MessagesRemarkReadRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-14 15:35
 */
public class Main {

    @Autowired
    private MessagesClient messagesClient;


    public static void main(String[] args) {



        User user = new User.Builder("zhengxuan","123456")
                        .age(23).phoneNumber("18383380616").address("四川省成都市").build();
        Map<String,Object> map = new HashMap<>(16);

        MessagesClient messagesClient = new MessageClientImpl("http://192.168.103.28:8087");
        MessagesRemarkReadRequest condition = new MessagesRemarkReadRequest();
        condition.setAppId("73F845EAB9A865D87AE338B7FC87AE04");
        condition.setAppKey("A4848E65D8942E9657FA7EE96C4C950C");
        condition.setPlatformId("0B690261FDD33D4C7339A8C1287AEFDE");
        condition.setReceiver("9");
        condition.setRemarkAll(1);
        Result result = messagesClient.remarkRead(condition);
        System.out.println("返回结果:"+result);
        if("0".equals(result.getCode())){
            System.out.println("调用成功");
        }else{
            System.out.println("调用失败");
        }
    }
}
