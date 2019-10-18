package com.zhliang.springboot.produce;

import com.zhliang.springboot.produce.entity.Order;
import com.zhliang.springboot.produce.produce.RabbitOrderSender;
import com.zhliang.springboot.produce.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootPorduceApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private RabbitOrderSender orderSender;

    @Test
    public void test() {
        Order order = new Order();
        order.setId("201904270000000000000000001");
        order.setName("鲁班七号");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());

        orderSender.sendOrder(order);
    }

    @Autowired
    private OrderService orderService;

    /**
     * 消息发送成功但confirm返回ack=false: https://blog.csdn.net/dh554112075/article/details/90137869
     * ★ 加休眠时间：
     *    是因为当测试方法结束时，rabbitMQ相关的资源也就关闭了，虽然消息发送出去了，但异步的
     *    ConfirmCallback却由于资源关闭而返回 ack=false
     * @throws InterruptedException
     */
    @Test
    public void testCreateOrder() throws InterruptedException {
        Order order = new Order();
        order.setId("201905200000002");
        order.setName("测试创建订单");
        order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());
        orderService.createOrder(order);

        Thread.sleep(2000);
    }


    private static String exChange = "DIRECT_EX";

    /**
     * [RabbitMQ(四)消息确认(发送确认,接收确认)]
     *       ：https://blog.csdn.net/qq315737546/article/details/54176560
     * 消息路由正确并发送到queue confirm被回调：ack=true
     */
    @Test
    public void test1() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        System.out.println("test1---message:"+message);
        //exchange,queue 都正确,confirm被回调, ack=true
        orderSender.send(exChange,"CONFIRM_TEST",message);
        Thread.sleep(1000);
    }

    /**
     * 消息路由错误 没有发送到queue confirm被回调：ack=false
     */
    @Test
    public void test2() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        System.out.println("test2---message:"+message);
        //exchange 错误,queue 正确,confirm被回调, ack=false
        orderSender.send(exChange+"NO","CONFIRM_TEST",message);
        Thread.sleep(1000);
    }

    /**
     * 消息路由正确 queue错误，消息没有发送到queue
     * - confirm被回调：ack=true
     * - return被回调   replyText:NO_ROUTE
     */
    @Test
    public void test3() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        System.out.println("test3---message:"+message);
        //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
        orderSender.send(exChange,"",message);
        Thread.sleep(1000);
    }


    /**
     * 消息路由错误 queue错误，消息没有发送到queue
     * - confirm被回调：ack=false
     */
    @Test
    public void test4() throws InterruptedException{
        String message = "currentTime:"+System.currentTimeMillis();
        System.out.println("test4---message:"+message);
        //exchange 错误,queue 错误,confirm被回调, ack=false
        orderSender.send(exChange+"NO","CONFIRM_TEST",message);
        Thread.sleep(1000);
    }
}
