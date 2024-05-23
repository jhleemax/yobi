package com.example.yobi.Retrofit.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeInfo {
    @SerializedName("일련번호")
    @Expose
    private String RCP_SEQ;
    @SerializedName("메뉴명")
    @Expose
    private String RCP_NM;
    @SerializedName("조리방법")
    @Expose
    private String RCP_WAY2;
    @SerializedName("요리종류")
    @Expose
    private String RCP_PAT2;
    @SerializedName("중량")
    @Expose
    private String INFO_WGT;
    @SerializedName("열량")
    @Expose
    private String INFO_ENG;
    @SerializedName("탄수화물")
    @Expose
    private String INFO_CAR;
    @SerializedName("단백질")
    @Expose
    private String INFO_PRO;
    @SerializedName("지방")
    @Expose
    private String INFO_FAT;
    @SerializedName("나트륨")
    @Expose
    private String INFO_NA;
    @SerializedName("해쉬태그")
    @Expose
    private String HASH_TAG;
    @SerializedName("이미지경로_소")
    @Expose
    private String ATT_FILE_NO_MAIN;
    @SerializedName("이미지경로_대")
    @Expose
    private String ATT_FILE_NO_MK;
    @SerializedName("재료정보")
    @Expose
    private String RCP_PARTS_DTLS;
    @SerializedName("저감조리법")
    @Expose
    private String RCP_NA_TIP;

    // 만드는법 & 이미지
    @SerializedName("만드는법_01")
    @Expose
    private String MANUAL01;
    @SerializedName("만드는법_이미지_01")
    @Expose
    private String MANUAL_IMG01;
    @SerializedName("만드는법_02")
    @Expose
    private String MANUAL02;
    @SerializedName("만드는법_이미지_02")
    @Expose
    private String MANUAL_IMG02;
    @SerializedName("만드는법_03")
    @Expose
    private String MANUAL03;
    @SerializedName("만드는법_이미지_03")
    @Expose
    private String MANUAL_IMG03;
    @SerializedName("만드는법_04")
    @Expose
    private String MANUAL04;
    @SerializedName("만드는법_이미지_04")
    @Expose
    private String MANUAL_IMG04;
    @SerializedName("만드는법_05")
    @Expose
    private String MANUAL05;
    @SerializedName("만드는법_이미지_05")
    @Expose
    private String MANUAL_IMG05;
    @SerializedName("만드는법_06")
    @Expose
    private String MANUAL06;
    @SerializedName("만드는법_이미지_06")
    @Expose
    private String MANUAL_IMG06;
    @SerializedName("만드는법_07")
    @Expose
    private String MANUAL07;
    @SerializedName("만드는법_이미지_07")
    @Expose
    private String MANUAL_IMG07;
    @SerializedName("만드는법_08")
    @Expose
    private String MANUAL08;
    @SerializedName("만드는법_이미지_08")
    @Expose
    private String MANUAL_IMG08;
    @SerializedName("만드는법_09")
    @Expose
    private String MANUAL09;
    @SerializedName("만드는법_이미지_09")
    @Expose
    private String MANUAL_IMG09;
    @SerializedName("만드는법_10")
    @Expose
    private String MANUAL10;
    @SerializedName("만드는법_이미지_10")
    @Expose
    private String MANUAL_IMG10;
    @SerializedName("만드는법_11")
    @Expose
    private String MANUAL11;
    @SerializedName("만드는법_이미지_11")
    @Expose
    private String MANUAL_IMG11;
    @SerializedName("만드는법_12")
    @Expose
    private String MANUAL12;
    @SerializedName("만드는법_이미지_12")
    @Expose
    private String MANUAL_IMG12;
    @SerializedName("만드는법_13")
    @Expose
    private String MANUAL13;
    @SerializedName("만드는법_이미지_13")
    @Expose
    private String MANUAL_IMG13;
    @SerializedName("만드는법_14")
    @Expose
    private String MANUAL14;
    @SerializedName("만드는법_이미지_14")
    @Expose
    private String MANUAL_IMG14;
    @SerializedName("만드는법_15")
    @Expose
    private String MANUAL15;
    @SerializedName("만드는법_이미지_15")
    @Expose
    private String MANUAL_IMG15;
    @SerializedName("만드는법_16")
    @Expose
    private String MANUAL16;
    @SerializedName("만드는법_이미지_16")
    @Expose
    private String MANUAL_IMG16;
    @SerializedName("만드는법_17")
    @Expose
    private String MANUAL17;
    @SerializedName("만드는법_이미지_17")
    @Expose
    private String MANUAL_IMG17;
    @SerializedName("만드는법_18")
    @Expose
    private String MANUAL18;
    @SerializedName("만드는법_이미지_18")
    @Expose
    private String MANUAL_IMG18;
    @SerializedName("만드는법_19")
    @Expose
    private String MANUAL19;
    @SerializedName("만드는법_이미지_19")
    @Expose
    private String MANUAL_IMG19;
    @SerializedName("만드는법_20")
    @Expose
    private String MANUAL20;
    @SerializedName("만드는법_이미지_20")
    @Expose
    private String MANUAL_IMG20;

    public RecipeInfo(String rcpSeq, String rcpNm, String rcpWay2, String rcpPat2, String infoWgt, String infoEng, String infoCar, String infoPro, String infoFat, String infoNa, String hashTag, String attFileNoMain, String attFileNoMk, String rcpPartsDtls, String rcpNaTip, String manual01, String manualImg01, String manual02, String manualImg02, String manual03, String manualImg03, String manual04, String manualImg04, String manual05, String manualImg05, String manual06, String manualImg06, String manual07, String manualImg07, String manual08, String manualImg08, String manual09, String manualImg09, String manual10, String manualImg10, String manual11, String manualImg11, String manual12, String manualImg12, String manual13, String manualImg13, String manual14, String manualImg14, String manual15, String manualImg15, String manual16, String manualImg16, String manual17, String manualImg17, String manual18, String manualImg18, String manual19, String manualImg19, String manual20, String manualImg20) {
        // 생성자
    }


    // getter & setter

    public String getRCP_SEQ() {
        return RCP_SEQ;
    }

    public void setRCP_SEQ(String RCP_SEQ) {
        this.RCP_SEQ = RCP_SEQ;
    }

    public String getRCP_NM() {
        return RCP_NM;
    }

    public void setRCP_NM(String RCP_NM) {
        this.RCP_NM = RCP_NM;
    }

    public String getRCP_WAY2() {
        return RCP_WAY2;
    }

    public void setRCP_WAY2(String RCP_WAY2) {
        this.RCP_WAY2 = RCP_WAY2;
    }

    public String getRCP_PAT2() {
        return RCP_PAT2;
    }

    public void setRCP_PAT2(String RCP_PAT2) {
        this.RCP_PAT2 = RCP_PAT2;
    }

    public String getINFO_WGT() {
        return INFO_WGT;
    }

    public void setINFO_WGT(String INFO_WGT) {
        this.INFO_WGT = INFO_WGT;
    }

    public String getINFO_ENG() {
        return INFO_ENG;
    }

    public void setINFO_ENG(String INFO_ENG) {
        this.INFO_ENG = INFO_ENG;
    }

    public String getINFO_CAR() {
        return INFO_CAR;
    }

    public void setINFO_CAR(String INFO_CAR) {
        this.INFO_CAR = INFO_CAR;
    }

    public String getINFO_PRO() {
        return INFO_PRO;
    }

    public void setINFO_PRO(String INFO_PRO) {
        this.INFO_PRO = INFO_PRO;
    }

    public String getINFO_FAT() {
        return INFO_FAT;
    }

    public void setINFO_FAT(String INFO_FAT) {
        this.INFO_FAT = INFO_FAT;
    }

    public String getINFO_NA() {
        return INFO_NA;
    }

    public void setINFO_NA(String INFO_NA) {
        this.INFO_NA = INFO_NA;
    }

    public String getHASH_TAG() {
        return HASH_TAG;
    }

    public void setHASH_TAG(String HASH_TAG) {
        this.HASH_TAG = HASH_TAG;
    }

    public String getATT_FILE_NO_MAIN() {
        return ATT_FILE_NO_MAIN;
    }

    public void setATT_FILE_NO_MAIN(String ATT_FILE_NO_MAIN) {
        this.ATT_FILE_NO_MAIN = ATT_FILE_NO_MAIN;
    }

    public String getATT_FILE_NO_MK() {
        return ATT_FILE_NO_MK;
    }

    public void setATT_FILE_NO_MK(String ATT_FILE_NO_MK) {
        this.ATT_FILE_NO_MK = ATT_FILE_NO_MK;
    }

    public String getRCP_PARTS_DTLS() {
        return RCP_PARTS_DTLS;
    }

    public void setRCP_PARTS_DTLS(String RCP_PARTS_DTLS) {
        this.RCP_PARTS_DTLS = RCP_PARTS_DTLS;
    }

    public String getRCP_NA_TIP() {
        return RCP_NA_TIP;
    }

    public void setRCP_NA_TIP(String RCP_NA_TIP) {
        this.RCP_NA_TIP = RCP_NA_TIP;
    }

    public String getMANUAL01() {
        return MANUAL01;
    }

    public void setMANUAL01(String MANUAL01) {
        this.MANUAL01 = MANUAL01;
    }

    public String getMANUAL_IMG01() {
        return MANUAL_IMG01;
    }

    public void setMANUAL_IMG01(String MANUAL_IMG01) {
        this.MANUAL_IMG01 = MANUAL_IMG01;
    }

    public String getMANUAL02() {
        return MANUAL02;
    }

    public void setMANUAL02(String MANUAL02) {
        this.MANUAL02 = MANUAL02;
    }

    public String getMANUAL_IMG02() {
        return MANUAL_IMG02;
    }

    public void setMANUAL_IMG02(String MANUAL_IMG02) {
        this.MANUAL_IMG02 = MANUAL_IMG02;
    }

    public String getMANUAL03() {
        return MANUAL03;
    }

    public void setMANUAL03(String MANUAL03) {
        this.MANUAL03 = MANUAL03;
    }

    public String getMANUAL_IMG03() {
        return MANUAL_IMG03;
    }

    public void setMANUAL_IMG03(String MANUAL_IMG03) {
        this.MANUAL_IMG03 = MANUAL_IMG03;
    }

    public String getMANUAL04() {
        return MANUAL04;
    }

    public void setMANUAL04(String MANUAL04) {
        this.MANUAL04 = MANUAL04;
    }

    public String getMANUAL_IMG04() {
        return MANUAL_IMG04;
    }

    public void setMANUAL_IMG04(String MANUAL_IMG04) {
        this.MANUAL_IMG04 = MANUAL_IMG04;
    }

    public String getMANUAL05() {
        return MANUAL05;
    }

    public void setMANUAL05(String MANUAL05) {
        this.MANUAL05 = MANUAL05;
    }

    public String getMANUAL_IMG05() {
        return MANUAL_IMG05;
    }

    public void setMANUAL_IMG05(String MANUAL_IMG05) {
        this.MANUAL_IMG05 = MANUAL_IMG05;
    }

    public String getMANUAL06() {
        return MANUAL06;
    }

    public void setMANUAL06(String MANUAL06) {
        this.MANUAL06 = MANUAL06;
    }

    public String getMANUAL_IMG06() {
        return MANUAL_IMG06;
    }

    public void setMANUAL_IMG06(String MANUAL_IMG06) {
        this.MANUAL_IMG06 = MANUAL_IMG06;
    }

    public String getMANUAL07() {
        return MANUAL07;
    }

    public void setMANUAL07(String MANUAL07) {
        this.MANUAL07 = MANUAL07;
    }

    public String getMANUAL_IMG07() {
        return MANUAL_IMG07;
    }

    public void setMANUAL_IMG07(String MANUAL_IMG07) {
        this.MANUAL_IMG07 = MANUAL_IMG07;
    }

    public String getMANUAL08() {
        return MANUAL08;
    }

    public void setMANUAL08(String MANUAL08) {
        this.MANUAL08 = MANUAL08;
    }

    public String getMANUAL_IMG08() {
        return MANUAL_IMG08;
    }

    public void setMANUAL_IMG08(String MANUAL_IMG08) {
        this.MANUAL_IMG08 = MANUAL_IMG08;
    }

    public String getMANUAL09() {
        return MANUAL09;
    }

    public void setMANUAL09(String MANUAL09) {
        this.MANUAL09 = MANUAL09;
    }

    public String getMANUAL_IMG09() {
        return MANUAL_IMG09;
    }

    public void setMANUAL_IMG09(String MANUAL_IMG09) {
        this.MANUAL_IMG09 = MANUAL_IMG09;
    }

    public String getMANUAL10() {
        return MANUAL10;
    }

    public void setMANUAL10(String MANUAL10) {
        this.MANUAL10 = MANUAL10;
    }

    public String getMANUAL_IMG10() {
        return MANUAL_IMG10;
    }

    public void setMANUAL_IMG10(String MANUAL_IMG10) {
        this.MANUAL_IMG10 = MANUAL_IMG10;
    }

    public String getMANUAL11() {
        return MANUAL11;
    }

    public void setMANUAL11(String MANUAL11) {
        this.MANUAL11 = MANUAL11;
    }

    public String getMANUAL_IMG11() {
        return MANUAL_IMG11;
    }

    public void setMANUAL_IMG11(String MANUAL_IMG11) {
        this.MANUAL_IMG11 = MANUAL_IMG11;
    }

    public String getMANUAL12() {
        return MANUAL12;
    }

    public void setMANUAL12(String MANUAL12) {
        this.MANUAL12 = MANUAL12;
    }

    public String getMANUAL_IMG12() {
        return MANUAL_IMG12;
    }

    public void setMANUAL_IMG12(String MANUAL_IMG12) {
        this.MANUAL_IMG12 = MANUAL_IMG12;
    }

    public String getMANUAL13() {
        return MANUAL13;
    }

    public void setMANUAL13(String MANUAL13) {
        this.MANUAL13 = MANUAL13;
    }

    public String getMANUAL_IMG13() {
        return MANUAL_IMG13;
    }

    public void setMANUAL_IMG13(String MANUAL_IMG13) {
        this.MANUAL_IMG13 = MANUAL_IMG13;
    }

    public String getMANUAL14() {
        return MANUAL14;
    }

    public void setMANUAL14(String MANUAL14) {
        this.MANUAL14 = MANUAL14;
    }

    public String getMANUAL_IMG14() {
        return MANUAL_IMG14;
    }

    public void setMANUAL_IMG14(String MANUAL_IMG14) {
        this.MANUAL_IMG14 = MANUAL_IMG14;
    }

    public String getMANUAL15() {
        return MANUAL15;
    }

    public void setMANUAL15(String MANUAL15) {
        this.MANUAL15 = MANUAL15;
    }

    public String getMANUAL_IMG15() {
        return MANUAL_IMG15;
    }

    public void setMANUAL_IMG15(String MANUAL_IMG15) {
        this.MANUAL_IMG15 = MANUAL_IMG15;
    }

    public String getMANUAL16() {
        return MANUAL16;
    }

    public void setMANUAL16(String MANUAL16) {
        this.MANUAL16 = MANUAL16;
    }

    public String getMANUAL_IMG16() {
        return MANUAL_IMG16;
    }

    public void setMANUAL_IMG16(String MANUAL_IMG16) {
        this.MANUAL_IMG16 = MANUAL_IMG16;
    }

    public String getMANUAL17() {
        return MANUAL17;
    }

    public void setMANUAL17(String MANUAL17) {
        this.MANUAL17 = MANUAL17;
    }

    public String getMANUAL_IMG17() {
        return MANUAL_IMG17;
    }

    public void setMANUAL_IMG17(String MANUAL_IMG17) {
        this.MANUAL_IMG17 = MANUAL_IMG17;
    }

    public String getMANUAL18() {
        return MANUAL18;
    }

    public void setMANUAL18(String MANUAL18) {
        this.MANUAL18 = MANUAL18;
    }

    public String getMANUAL_IMG18() {
        return MANUAL_IMG18;
    }

    public void setMANUAL_IMG18(String MANUAL_IMG18) {
        this.MANUAL_IMG18 = MANUAL_IMG18;
    }

    public String getMANUAL19() {
        return MANUAL19;
    }

    public void setMANUAL19(String MANUAL19) {
        this.MANUAL19 = MANUAL19;
    }

    public String getMANUAL_IMG19() {
        return MANUAL_IMG19;
    }

    public void setMANUAL_IMG19(String MANUAL_IMG19) {
        this.MANUAL_IMG19 = MANUAL_IMG19;
    }

    public String getMANUAL20() {
        return MANUAL20;
    }

    public void setMANUAL20(String MANUAL20) {
        this.MANUAL20 = MANUAL20;
    }

    public String getMANUAL_IMG20() {
        return MANUAL_IMG20;
    }

    public void setMANUAL_IMG20(String MANUAL_IMG20) {
        this.MANUAL_IMG20 = MANUAL_IMG20;
    }
}
