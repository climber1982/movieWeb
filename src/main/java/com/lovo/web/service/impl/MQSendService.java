package com.lovo.web.service.impl;

import com.lovo.web.entity.OrderEntity;
import com.lovo.web.service.ITicketService;
import com.lovo.web.util.StringUtil;
import com.lovo.web.vo.UserVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service(value = "mQSendService")
public class MQSendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
     private ITicketService ticketService;
    //消息确认
    RabbitTemplate.ConfirmCallback confirmCallback= new  RabbitTemplate.ConfirmCallback(){

        public void confirm(CorrelationData correlationData, boolean b, String s) {
               //发送成功把
        String tag=    correlationData.getReturnedMessage()
                    .getMessageProperties()
                    .getHeaders()
                    .get("tag").toString();
         String id=correlationData.getId();
          if(tag.equals("pay")){
              //支付的回复，修改本地数据库为成功
              ticketService.updateOrderBynum(id, StringUtil.PAY);
          }else if(tag.equals("ticket")){
              //出票成功
              //支付的回复，修改本地数据库为成功
              ticketService.updateOrderBynum(id, StringUtil.TICKET_YES);
          }
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
        MessageProperties properties=new MessageProperties();
        properties.setHeader("tag","pay");
        Message message=new Message(null,properties);
        correlationData.setReturnedMessage(message);
        //发送
        rabbitTemplate.convertAndSend("directExchange","direct.payQueue",orderEntity,correlationData);
    }
     //出票
    public void sendTicket(OrderEntity orderEntity){
        rabbitTemplate.setMandatory(true);//开启消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //把订单号做为数据关联
        CorrelationData correlationData=new CorrelationData(orderEntity.getOrderNum());
        MessageProperties properties=new MessageProperties();
        properties.setHeader("tag","ticket");
        Message message=new Message(null,properties);
        correlationData.setReturnedMessage(message);
        //发送
        rabbitTemplate.convertAndSend("directExchange","direct.movieQueue",orderEntity,correlationData);
    }




}
