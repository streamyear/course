package com.streamyear.course.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "eventQueue")
public class EventQueue {

    @RabbitHandler
    public void process(String manager){
        System.out.println("eventQueue收到的消息内容: " + manager);
    }
}
