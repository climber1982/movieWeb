package com.lovo.web.service;


import com.lovo.web.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("userServer")
public interface IUserService {
     @RequestMapping("/userInfo/{userName}/{password}")
    public UserEntity userInfo(@PathVariable("userName")String userName,
                               @PathVariable("password")String password);
}
