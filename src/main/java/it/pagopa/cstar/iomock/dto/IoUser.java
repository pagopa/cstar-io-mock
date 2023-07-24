package it.pagopa.cstar.iomock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class IoUser {
    @JsonProperty("name")
    private String name;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("spid_email")
    private String spidEmail;

    @JsonProperty("notice_email")
    private String noticeEmail;

    @JsonProperty("mobile_phone")
    private String mobilePhone;

    @JsonProperty("fiscal_code")
    private String fiscalCode;
}
