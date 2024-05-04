package com.yobi.yobiproject.defRecipe.Entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "defrecipe")
public class DefRecipe {
    @Id
    @Column(name = "RCP_SEQ")
    private String RCP_SEQ;

    @Column(name = "RCP_NM")
    private String RCP_NM;

    @Column(name = "RCP_WAY2")
    private String RCP_WAY2;

    @Column(name = "RCP_PAT2")
    private String RCP_PAT2;

    @Column(name = "INFO_WGT")
    private String INFO_WGT;

    @Column(name = "INFO_ENG")
    private String INFO_ENG;

    @Column(name = "INFO_CAR")
    private String INFO_CAR;

    @Column(name = "INFO_PRO")
    private String INFO_PRO;

    @Column(name = "INFO_FAT")
    private String INFO_FAT;

    @Column(name = "INFO_NA")
    private String INFO_NA;

    @Column(name = "HASH_TAG")
    private String HASH_TAG;

    @Column(name = "ATT_FILE_NO_MAIN")
    private String ATT_FILE_NO_MAIN;

    @Column(name = "ATT_FILE_NO_MK")
    private String ATT_FILE_NO_MK;

    @Column(name = "RCP_PARTS_DTLS")
    private String RCP_PARTS_DTLS;

    @Column(name = "MANUAL01")
    private String MANUAL01;
    @Column(name = "MANUAL_IMG01")
    private String MANUAL_IMG01;

    @Column(name = "MANUAL02")
    private String MANUAL02;
    @Column(name = "MANUAL_IMG02")
    private String MANUAL_IMG02;

    @Column(name = "MANUAL03")
    private String MANUAL03;
    @Column(name = "MANUAL_IMG03")
    private String MANUAL_IMG03;

    @Column(name = "MANUAL04")
    private String MANUAL04;
    @Column(name = "MANUAL_IMG04")
    private String MANUAL_IMG04;

    @Column(name = "MANUAL05")
    private String MANUAL05;
    @Column(name = "MANUAL_IMG05")
    private String MANUAL_IMG05;

    @Column(name = "MANUAL06")
    private String MANUAL06;
    @Column(name = "MANUAL_IMG06")
    private String MANUAL_IMG06;

    @Column(name = "MANUAL07")
    private String MANUAL07;
    @Column(name = "MANUAL_IMG07")
    private String MANUAL_IMG07;

    @Column(name = "MANUAL08")
    private String MANUAL08;
    @Column(name = "MANUAL_IMG08")
    private String MANUAL_IMG08;

    @Column(name = "MANUAL09")
    private String MANUAL09;
    @Column(name = "MANUAL_IMG09")
    private String MANUAL_IMG09;

    @Column(name = "MANUAL10")
    private String MANUAL10;
    @Column(name = "MANUAL_IMG10")
    private String MANUAL_IMG10;

    @Column(name = "MANUAL11")
    private String MANUAL11;
    @Column(name = "MANUAL_IMG11")
    private String MANUAL_IMG11;

    @Column(name = "MANUAL12")
    private String MANUAL12;
    @Column(name = "MANUAL_IMG12")
    private String MANUAL_IMG12;

    @Column(name = "MANUAL13")
    private String MANUAL13;
    @Column(name = "MANUAL_IMG13")
    private String MANUAL_IMG13;

    @Column(name = "MANUAL14")
    private String MANUAL14;
    @Column(name = "MANUAL_IMG14")
    private String MANUAL_IMG14;

    @Column(name = "MANUAL15")
    private String MANUAL15;
    @Column(name = "MANUAL_IMG15")
    private String MANUAL_IMG15;

    @Column(name = "MANUAL16")
    private String MANUAL16;
    @Column(name = "MANUAL_IMG16")
    private String MANUAL_IMG16;

    @Column(name = "MANUAL17")
    private String MANUAL17;
    @Column(name = "MANUAL_IMG17")
    private String MANUAL_IMG17;

    @Column(name = "MANUAL18")
    private String MANUAL18;
    @Column(name = "MANUAL_IMG18")
    private String MANUAL_IMG18;

    @Column(name = "MANUAL19")
    private String MANUAL19;
    @Column(name = "MANUAL_IMG19")
    private String MANUAL_IMG19;

    @Column(name = "MANUAL20")
    private String MANUAL20;
    @Column(name = "MANUAL_IMG20")
    private String MANUAL_IMG20;

    @Column(name = "RCP_NA_TIP")
    private String RCP_NA_TIP;
}
