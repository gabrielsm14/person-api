package br.com.empresa.personapi.utils;

import br.com.empresa.personapi.dto.PhoneDTO;
import br.com.empresa.personapi.entity.Phone;
import br.com.empresa.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "1198965-9988";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
