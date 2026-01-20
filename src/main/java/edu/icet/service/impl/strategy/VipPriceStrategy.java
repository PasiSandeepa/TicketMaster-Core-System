package edu.icet.service.impl.strategy;

import edu.icet.model.entity.EventEntity;
import edu.icet.service.PriceStrategy;
import org.springframework.stereotype.Component;

@Component("VIP")
public class VipPriceStrategy implements PriceStrategy {
    @Override
    public Double calculate(Double basePrice, EventEntity event) {
        if (Boolean.TRUE.equals(event.getIsHighDemand())) {
            return basePrice;
        }
        return basePrice * 0.9;
    }
}