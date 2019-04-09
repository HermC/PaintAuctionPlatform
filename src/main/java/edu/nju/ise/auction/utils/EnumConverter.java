package edu.nju.ise.auction.utils;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public class EnumConverter<E extends Enum<E>> implements AttributeConverter<E, String> {

    private Class<E> enumClass;

    @Override
    public String convertToDatabaseColumn(E e) {
        return e.name().toUpperCase();
    }

    @Override
    public E convertToEntityAttribute(String s) {
        for (E en: EnumSet.allOf(enumClass)) {
            if (en.name().equalsIgnoreCase(s)) {
                return en;
            }
        }
        return null;
    }

}
