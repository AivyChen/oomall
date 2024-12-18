package cn.edu.xmu.oomall.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*Restful的Controller对象*/
@RequiredArgsConstructor
@RequestMapping(produces = "application/json;charset=UTF-8")
public class AdminController {
}
