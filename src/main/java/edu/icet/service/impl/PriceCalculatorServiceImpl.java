package edu.icet.service.impl;

import edu.icet.model.entity.EventEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.service.PriceCalculatorService;
import edu.icet.service.PriceStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PriceCalculatorServiceImpl implements PriceCalculatorService {
    private final Map<String, PriceStrategy> pricingStrategies;

    @Override
    public Double calculatePrice(UserEntity user, EventEntity event) {
        String tier = user.getTier().toUpperCase();
        PriceStrategy strategy = pricingStrategies.get(tier);

        if (strategy == null) {
            throw new RuntimeException("No pricing strategy found for tier: " + tier);
        }

        return strategy.calculate(event.getBasePrice(), event);
    }
}