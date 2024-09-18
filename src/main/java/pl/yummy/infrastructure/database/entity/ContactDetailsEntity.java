package pl.yummy.infrastructure.database.entity;

import pl.yummy.infrastructure.database.entity.enums.ContactType;

public class ContactDetailsEntity {

    private Integer contactDetailsId;
    private ContactType contactType;
    private String corporateName;
    private String name;
    private String surname;
    private String phone;
    private String email;
}
