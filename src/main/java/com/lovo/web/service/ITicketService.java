package com.lovo.web.service;

import com.lovo.web.entity.OrderEntity;
import com.lovo.web.entity.TicketEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("movieService")
public interface ITicketService {

    /**
     * 查询出所有的票
     * @return
     */
    @RequestMapping(value = "findAllTicket")
    public List<TicketEntity> findAllTicket();

    @RequestMapping("ticketByIndex/{index}")
    public TicketEntity ticketByIndex(@PathVariable("index") int index);

    @RequestMapping("savaOrder")
    public void savaOrder(OrderEntity orderEntity);

    @RequestMapping("updateTicketNum/{index}")
    public String  updateTicketNum(@PathVariable("index") int index);

    /**
     * 根据订单编获取订单
     * @param orderNum
     * @return
     */
    @RequestMapping("OrderEntityByOrderNum/{orderNum}")
    public OrderEntity getOrderEntityByOrderNum(@PathVariable("orderNum") String orderNum);
}
