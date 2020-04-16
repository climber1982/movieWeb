package com.lovo.web.service;

import com.lovo.web.entity.OrderEntity;

public interface ITicketLService {
    public OrderEntity buyTicket(int index, String userName);
}
