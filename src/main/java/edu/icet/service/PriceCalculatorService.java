package edu.icet.service;

import edu.icet.model.entity.EventEntity;
import edu.icet.model.entity.UserEntity;

public interface PriceCalculatorService {
    Double calculatePrice(UserEntity user, EventEntity event);
}