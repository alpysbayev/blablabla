package com.prometeo.drp_final.converter;

public interface DtoConverter<E, D> {

    D convertToDto(E e);

    void updateFromDto(E e, D dto);

    default void createFromDto(E e, D dto) {
        updateFromDto(e, dto);
    }


}
