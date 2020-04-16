package com.lovo.web.config;

import com.lovo.web.util.HeaderInterceptor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig  {
    @Autowired
    CachingConnectionFactory connectionFactory;
    //header拦截器交给容器
    @Bean
    public HeaderInterceptor headerInterceptor(){
        return  new HeaderInterceptor();
    }
    //支付队列
    @Bean
    public Queue  payQueue(){
        return new Queue("payQueue");
    }
    //电影院的队列
    @Bean
    public  Queue  movieQueue(){
        return  new Queue("movieQueue");
    }

    //交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    //绑定交换机
    @Bean
    public Binding directExchangeToPayQueue(Queue  payQueue,DirectExchange directExchange){
      return BindingBuilder.bind(payQueue).to(directExchange).with("direct.payQueue");
    }
    @Bean
    public Binding directExchangeToMovieQueue(Queue  movieQueue,DirectExchange directExchange){
        return BindingBuilder.bind(movieQueue).to(directExchange).with("direct.movieQueue");
    }

    //创建AMP模板
    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory);
    }


}
