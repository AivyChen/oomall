package cn.edu.xmu.oomall.freight.controller;

import cn.edu.xmu.javaee.core.aop.Audit;
import cn.edu.xmu.javaee.core.aop.LoginUser;
import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.javaee.core.util.CloneFactory;
import cn.edu.xmu.oomall.freight.controller.dto.UndeliverableDto;
import cn.edu.xmu.oomall.freight.dao.bo.Undeliverable;
import cn.edu.xmu.oomall.freight.service.LogisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cn.edu.xmu.javaee.core.model.Constants.PLATFORM;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/platforms/{shopId}", produces = "application/json;charset=UTF-8")
public class PlatformController {
    private final LogisticsService logisticsService;

    /**
     * 2024-dsg-112
     * 商家指定无法配送
     *
     */
    @PostMapping("/logistics/{id}/regions/{rid}/undeliverable")
    @Audit(departName = "platforms")
    public ReturnObject addUndeliverableRegion(@PathVariable Long shopId,
                                               @PathVariable Long rid,
                                               @PathVariable Long id,
                                               @RequestBody UndeliverableDto undeliverableDto,
                                               @LoginUser UserDto user){
        if (Objects.equals(PLATFORM,shopId)) {
            Undeliverable undeliverable = CloneFactory.copy(new Undeliverable(), undeliverableDto);
            logisticsService.addUndeliverableRegion(undeliverable, user);
        }else{
            return new ReturnObject(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(), "未送达地区", rid, id));
        }
        return new ReturnObject(ReturnNo.CREATED);
    }


    /**
     * 2024-dsg-112
     * 商家删除无法配送
     *
     */
    @DeleteMapping("/logistics/{id}/regions/{rid}/undeliverable")
    @Audit(departName = "platforms")
    public ReturnObject deleteUndeliverableRegion(@PathVariable Long shopId,
                                                  @PathVariable Long rid,
                                                  @PathVariable Long id,
                                                  @LoginUser UserDto user){
        if (Objects.equals(PLATFORM,shopId)){
            logisticsService.deleteUndeliverableRegion(rid, id, user);
        }else{
            return new ReturnObject(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(), "未送达地区", rid, id));
        }
        return new ReturnObject();
    }

}
