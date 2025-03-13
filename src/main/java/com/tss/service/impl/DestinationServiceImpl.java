package com.tss.service.impl;

import com.tss.model.Destination;
import com.tss.repository.DestinationRepository;
import com.tss.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 目的地服务实现类
 */
@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getDestinationByCode(String code) {
        return destinationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("目的地代码不存在: " + code));
    }

    @Override
    public String getDestinationNameByCode(String code) {
        String name = destinationRepository.findNameByCode(code);
        if (name == null) {
            throw new RuntimeException("目的地代码不存在: " + code);
        }
        return name;
    }

    @Override
    public BigDecimal getBasePriceByCode(String code) {
        BigDecimal price = destinationRepository.findBasePriceByCode(code);
        if (price == null) {
            throw new RuntimeException("目的地代码不存在: " + code);
        }
        return price;
    }

    @Override
    public boolean isValidDestinationCode(String code) {
        return destinationRepository.findByCode(code).isPresent();
    }
}