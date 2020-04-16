package com.lovo.web.service.impl;

import com.lovo.web.entity.OrderEntity;
import com.lovo.web.entity.TicketEntity;
import com.lovo.web.service.ITicketLService;
import com.lovo.web.service.ITicketService;
import com.lovo.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service(value = "ticketLService")
public class TicketLServiceImpl implements ITicketLService {
    @Autowired
    private ITicketService ticketService;

    public OrderEntity buyTicket(int index, String userName) {
        boolean bl=false;


        return null;
    }
}
