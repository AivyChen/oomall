package cn.edu.xmu.oomall.freight.service;

import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.oomall.freight.dao.ExpressDao;
import cn.edu.xmu.oomall.freight.dao.LogisticsDao;
import cn.edu.xmu.oomall.freight.dao.ContractDao;
import cn.edu.xmu.oomall.freight.dao.bo.Express;
import cn.edu.xmu.oomall.freight.dao.bo.Logistics;
import cn.edu.xmu.oomall.freight.dao.bo.Contract;
import cn.edu.xmu.oomall.freight.dao.bo.Undeliverable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.edu.xmu.javaee.core.model.Constants.PLATFORM;
/**
 * @author fan ninghan
 * 2023-dng3-008
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class LogisticsService {

    private  final  ExpressDao expressDao;
    private final ContractDao contractDao;
    private final LogisticsDao logisticsDao;

    // 此处面向功能
    public Logistics findByBillCode(String billCode){
        Express express = this.expressDao.findByBillCode(PLATFORM, billCode);
        return express.getContract().getLogistics();
    }

    /**
     * 商铺指定无法配送地区
     *
     */
    public void addUndeliverableRegion(Undeliverable undeliverable, UserDto user){
        logisticsDao.insert(undeliverable, user);
    }


    /**
     * 商铺删除无法配送地区
     *
     */
    public void deleteUndeliverableRegion(Long regionId, Long logisticsId, UserDto user){
        logisticsDao.delete(regionId, logisticsId);
    }

    /**
     * 商铺查询无法配送地区
     *
     */
    public List<Undeliverable> retrieveUndeliverableRegion(Long logisticsId, Integer page, Integer pageSize){
        return logisticsDao.retrieveByLogisticsId(logisticsId, page, pageSize);
    }
}
