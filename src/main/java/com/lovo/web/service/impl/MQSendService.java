package com.lovo.web.service.impl;

import com.lovo.web.entity.OrderEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service(value = "mQSendService")
public class MQSendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //消息确认
    RabbitTemplate.ConfirmCallback confirmCallback= new  RabbitTemplate.ConfirmCallback(){

        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("correlationData="+correlationData);
            System.out.println("b="+b);
            System.out.println("s="+s);
            //写业务  如果 b=true 代表数据已经放入到队列，根据correlationData 修改本地数据tag=1()
        }
    };
    //回退
    RabbitTemplate.ReturnCallback returnCallback=new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.out.println("message="+message);
            System.out.println("i="+i);
            System.out.println("s="+s);
            System.out.println("s1="+s1);
            System.out.println("s2="+s2);
        }
    };
    //付款
    public void sendPay(OrderEntity orderEntity){
        rabbitTemplate.setMandatory(true);//开启消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //把订单号做为数据关联
        CorrelationData correlationData=new CorrelationData(orderEntity.getOrderNum());

        //发送
        rabbitTemplate.convertAndSend("directExchange","direct.payQueue",orderEntity,correlationData);
    }
     //出票
    public void sendTicket(OrderEntity orderEntity){

    }




}
