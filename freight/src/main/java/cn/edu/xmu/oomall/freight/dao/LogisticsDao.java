package cn.edu.xmu.oomall.freight.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.javaee.core.util.CloneFactory;
import cn.edu.xmu.oomall.freight.dao.bo.Logistics;
import cn.edu.xmu.oomall.freight.dao.bo.Undeliverable;
import cn.edu.xmu.oomall.freight.mapper.jpa.LogisticsPoMapper;
import cn.edu.xmu.oomall.freight.mapper.jpa.UndeliverablePoMapper;
import cn.edu.xmu.oomall.freight.mapper.po.LogisticsPo;
import cn.edu.xmu.oomall.freight.mapper.po.UndeliverablePo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/**
 * @author fan ninghan
 * 2023-dng3-008
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class LogisticsDao {
    private final LogisticsPoMapper logisticsPoMapper;
    private final UndeliverablePoMapper undeliverablePoMapper;
    private final RegionDao regionDao;

    /**
     * 查询物流
     *
     */
    public Logistics findById(Long id) throws RuntimeException {
        Optional<LogisticsPo> po = logisticsPoMapper.findById(id);
        if (po.isPresent()) {
            return CloneFactory.copy(new Logistics(), po.get());
        } else {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }

    private Undeliverable build(Undeliverable undeliverable){
        undeliverable.setRegionDao(this.regionDao);
        undeliverable.setLogisticsDao(this);
        return undeliverable;
    }

    public void insert(Undeliverable undeliverable, UserDto user) throws RuntimeException {
        // ensure regionId is valid
        undeliverable.setCreator(user);
        undeliverable.setGmtCreate(LocalDateTime.now());
        UndeliverablePo po = CloneFactory.copy(new UndeliverablePo(), undeliverable);
        log.debug("insert undeliverable = " + po);
        this.undeliverablePoMapper.save(po);
    }

    public void delete(Long regionId, Long logisticsId) throws RuntimeException {
        UndeliverablePo po = this.undeliverablePoMapper.findByRegionIdAndLogisticsId(regionId, logisticsId);
        if (Objects.isNull(po)) {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
        this.undeliverablePoMapper.deleteById(po.getId());
    }

    public List<Undeliverable> retrieveByLogisticsId(Long logisticsId, Integer page, Integer pageSize) throws RuntimeException {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<UndeliverablePo> pos = this.undeliverablePoMapper.findAllByLogisticsId(logisticsId, pageable);
        return this.undeliverablePoMapper.findAllByLogisticsId(logisticsId, pageable).stream()
                .map(po -> {
                    Undeliverable bo = CloneFactory.copy(new Undeliverable(), po);
                    try {
                        bo = this.build(bo);
                        return bo;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(java.util.stream.Collectors.toList());
    }

}
