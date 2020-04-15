package com.lovo.web.controller;

import com.lovo.web.entity.TicketEntity;
import com.lovo.web.service.ITicketService;
import com.lovo.web.vo.OrderVo;
import com.lovo.web.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private ITicketService ticketService;

    @RequestMapping("getListTicket")
    public ModelAndView getListTicket(){
        ModelAndView mv=new ModelAndView("listmovie");

        List<TicketEntity> ticketVoList= ticketService.findAllTicket();
        mv.addObject("listTiket",ticketVoList);
        return mv;
    }
    @RequestMapping("gotoOrder")
    public ModelAndView  gotoOrder(int index){
        ModelAndView mv=new ModelAndView("order");
        //根据index去查询出电影信息
        //电影票预库存
        //生成订单
        OrderVo vo=new OrderVo();
        vo.setIndex(index);
        vo.setMovieName("复仇者联盟1");
        vo.setOrderNum("2020414001");
        vo.setTickePrice(81);
        vo.setTickeNum(1);
        mv.addObject("orderInfo",vo);

        return  mv;
    }

   @RequestMapping("gotoPay")
    public ModelAndView gotoPay(String orderNum){
        ModelAndView mv=new ModelAndView();
        //根据订单编号，查询出订单内容
       //调用付款接口进行付款
       //修改订单状态为已付款
         RedirectView rv=new RedirectView("orderList");
         mv.setView(rv);
        return  mv;
    }

    /**
     * 订单列表查询
     * @return
     */
    @RequestMapping("orderList")
    public  ModelAndView  orderList(){
        ModelAndView mv=new ModelAndView("orderList");
        List<OrderVo> listvo=new ArrayList<>();
        for (int i=1;i<5;i++) {
            OrderVo vo = new OrderVo();
            vo.setMovieName("复仇者联盟"+i);
            vo.setOrderNum("2020414001"+i);
            vo.setTickePrice(81);
            vo.setTickeNum(1);
            if(i%2==0) {
                vo.setTag(0);
            }else {
                vo.setTag(1);
            }
            listvo.add(vo);
        }
        mv.addObject("orderList",listvo);
        return mv;
    }
}
