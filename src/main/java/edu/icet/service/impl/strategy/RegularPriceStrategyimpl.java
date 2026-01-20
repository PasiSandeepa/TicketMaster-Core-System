package edu.icet.service.impl.strategy;

import edu.icet.model.entity.EventEntity;
import edu.icet.service.PriceStrategy;
import org.springframework.stereotype.Component;

@Component("REGULAR")
public class RegularPriceStrategyimpl implements PriceStrategy {
    @Override
    public Double calculate(Double basePrice, EventEntity event) {
        return basePrice;
    }
}
