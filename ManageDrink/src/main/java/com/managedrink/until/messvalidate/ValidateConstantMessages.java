package com.managedrink.until.messvalidate;

import com.managedrink.until.constants.ValidateConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ValidateConstantMessages {

    @Autowired
    private MessageSource messageSource;

    public String getNameDrinkNotEmptyMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.NAME_DRINK_NOT_EMPTY, null, locale);
    }

    public String getNameDrinkNotNullMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.NAME_DRINK_NOT_NULL, null, locale);
    }

    public String getNameDrinkMaxSizeMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.NAME_DRINK_MAX_SIZE, null, locale);
    }

    public String getDescriptionDrinkMaxSizeMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.DESCRIPTION_DRINK_MAX_SIZE, null, locale);
    }

    public String getPriceDrinkGreaterZeroMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.PRICE_DRINK_MUST_GREATER_ZERO, null, locale);
    }

    public String getNameToppingNotEmptyMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.NAME_TOPPING_NOT_EMPTY, null, locale);
    }

    public String getNameToppingNotNullMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.NAME_TOPPING_NOT_NULL, null, locale);
    }

    public String getPriceToppingGreaterZeroMessage(Locale locale) {
        return messageSource.getMessage(ValidateConstant.PRICE_TOPPING_MUST_GREATER_ZERO, null, locale);
    }
}
