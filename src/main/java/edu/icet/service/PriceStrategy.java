package edu.icet.service;

import edu.icet.model.entity.EventEntity;

public interface PriceStrategy {
    Double calculate(Double basePrice, EventEntity event);
}
