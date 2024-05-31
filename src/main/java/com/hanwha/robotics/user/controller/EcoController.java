package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eco")
public class EcoController {

    @GetMapping("/main")
    public String ecoSystemMain() {
        return "eco/ecosystem_main";
    }

    @GetMapping("/eyes")
    public String ecoSystem1() {
        return "eco/ecosystem_template_01_eyes";
    }

    @GetMapping("/screwdriver")
    public String ecoSystem2() {
        return "eco/ecosystem_template_02_screwdriver";
    }

    @GetMapping("/magbot")
    public String ecoSystem3() {
        return "eco/ecosystem_template_03_magbot";
    }

    @GetMapping("/phd")
    public String ecoSystem4() {
        return "eco/ecosystem_template_04_phd";
    }

    @GetMapping("/pickitm-hd")
    public String ecoSystem5() {
        return "eco/ecosystem_template_05_pickitm-hd";
    }

    @GetMapping("/hct1200")
    public String ecoSystem6() {
        return "eco/ecosystem_template_06_hct1200";
    }

    @GetMapping("/rodi-x")
    public String ecoSystem7() {
        return "eco/ecosystem_template_07_rodi_x";
    }


    @GetMapping("/vacuum")
    public String ecoSystem8() {
        return "eco/ecosystem_template_08_vacuum";
    }

    @GetMapping("/pickit-m")
    public String ecoSystem9() {
        return "eco/ecosystem_template_09_pickit_m";
    }

    @GetMapping("/srp")
    public String ecoSystem10() {
        return "eco/ecosystem_template_10_srp";
    }

    @GetMapping("/mt01-mt02")
    public String ecoSystem11() {
        return "eco/ecosystem_template_11_mt01_mt02";
    }

    @GetMapping("/air")
    public String ecoSystem12() {
        return "eco/ecosystem_template_12_air";
    }

    @GetMapping("/cognex")
    public String ecoSystem13() {
        return "eco/ecosystem_template_13_cognex";
    }

    @GetMapping("/mt05")
    public String ecoSystem14() {
        return "eco/ecosystem_template_14_mt05";
    }

    @GetMapping("/screen-protector")
    public String ecoSystem15() {
        return "eco/ecosystem_template_15_screen_protector";
    }

    @GetMapping("/soft-grippers")
    public String ecoSystem16() {
        return "eco/ecosystem_template_16_soft_grippers";
    }

    @GetMapping("/mt04")
    public String ecoSystem17() {
        return "eco/ecosystem_template_17_mt04";
    }

    @GetMapping("/robotmaster-v7")
    public String ecoSystem18() {
        return "eco/ecosystem_template_18_robotmaster_v7";
    }

    @GetMapping("/3fg15")
    public String ecoSystem19() {
        return "eco/ecosystem_template_19_3fg15";
    }

    @GetMapping("/hi400p")
    public String ecoSystem20() {
        return "eco/ecosystem_template_20_hi400p";
    }

    @GetMapping("/automappers")
    public String ecoSystem21() {
        return "eco/ecosystem_template_21_automappers";
    }

    @GetMapping("/gecko-sp")
    public String ecoSystem22() {
        return "eco/ecosystem_template_22_gecko_sp";
    }

    @GetMapping("/ati-axia80")
    public String ecoSystem23() {
        return "eco/ecosystem_template_23_ati_axia80";
    }

    @GetMapping("/robotdk-simulation")
    public String ecoSystem24() {
        return "eco/ecosystem_template_24_robotdk_simulation";
    }

    @GetMapping("/vgc10")
    public String ecoSystem25() {
        return "eco/ecosystem_template_25_vgc10";
    }

    @GetMapping("/asycube50-asycube80")
    public String ecoSystem26() {
        return "eco/ecosystem_template_26_asycube50_asycube80";
    }

    @GetMapping("/sbot-speed")
    public String ecoSystem27() {
        return "eco/ecosystem_template_27_sbot_speed";
    }

    @GetMapping("/gecko-gripper")
    public String ecoSystem28() {
        return "eco/ecosystem_template_28_gecko_gripper";
    }

    @GetMapping("/asycube240")
    public String ecoSystem29() {
        return "eco/ecosystem_template_29_asycube240";
    }

    @GetMapping("/quick-changer")
    public String ecoSystem30() {
        return "eco/ecosystem_template_30_quick_changer";
    }

    @GetMapping("/rg2-ft")
    public String ecoSystem31() {
        return "eco/ecosystem_template_31_rg2_ft";
    }

    @GetMapping("/asycube380-asycube530")
    public String ecoSystem32() {
        return "eco/ecosystem_template_32_asycube380_asycube530";
    }

    @GetMapping("/coprotect")
    public String ecoSystem33() {
        return "eco/ecosystem_template_33_coprotect";
    }

    @GetMapping("/vg10")
    public String ecoSystem34() {
        return "eco/ecosystem_template_34_vg10";
    }

    @GetMapping("/smart2")
    public String ecoSystem35() {
        return "eco/ecosystem_template_35_smart2";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem36() {
        return "eco/ecosystem_template_36";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem37() {
        return "eco/ecosystem_template_37";
    }

    @GetMapping("/HRC-03-09950")
    public String ecoSystem38() {
        return "eco/ecosystem_template_38";
    }

    @GetMapping("/1a-cobot-dress-pack")
    public String ecoSystem39() {
        return "eco/ecosystem_template_39_cobot_dress";
    }

    @GetMapping("/hand-e")
    public String ecoSystem40() {
        return "eco/ecosystem_template_40_hand_e";
    }

    @GetMapping("/fhs-sh-set-hcr-series")
    public String ecoSystem41() {
        return "eco/ecosystem_template_41_fhs_sh_set";
    }

    @GetMapping("/2f-85-2f-140")
    public String ecoSystem42() {
        return "eco/ecosystem_template_42_2f_85_2f_140";
    }

    @GetMapping("/nt-50-re-en")
    public String ecoSystem43() {
        return "eco/ecosystem_template_43_nt_50_re";
    }

    @GetMapping("/ag-95-adaptive-gripper")
    public String ecoSystem44() {
        return "eco/ecosystem_template_44_ag_95_adaptive";
    }

    @GetMapping("/nt-100-re-en")
    public String ecoSystem45() {
        return "eco/ecosystem_template_45_nt_100_re_en";
    }

    @GetMapping("/phd-pneu-connect-s1-pneumatic-grippers")
    public String ecoSystem46() {
        return "eco/ecosystem_template_46_phd_pneu_connect";
    }

    @GetMapping("/nt-50-cc-en")
    public String ecoSystem47() {
        return "eco/ecosystem_template_47_nt_50_cc_en";
    }

    @GetMapping("/co-act-egp-c")
    public String ecoSystem48() {
        return "eco/ecosystem_template_48_co_act_egp_c";
    }

    @GetMapping("/hrc-03-086003")
    public String ecoSystem49() {
        return "eco/ecosystem_template_49_hrc_03_086003";
    }

    @GetMapping("/hrc-03-099507")
    public String ecoSystem50() {
        return "eco/ecosystem_template_50_hrc_03_099507";
    }

    @GetMapping("/jegb-4140")
    public String ecoSystem51() { return "ecosystem_template_51_jegb_4140"; }

    @GetMapping("/cogrip-drim-robotics")
    public String ecoSystem52() {
        return "eco/ecosystem_template_52_cogrip_drim_robotics";
    }

    @GetMapping("/cogrip-8")
    public String ecoSystem53() {
        return "eco/ecosystem_template_53_cogrip_8";
    }

    @GetMapping("/v-grip-system")
    public String ecoSystem54() {
        return "eco/ecosystem_template_54_v_grip_system";
    }

    @GetMapping("/magic-grippers")
    public String ecoSystem55() {
        return "eco/ecosystem_template_55_magic_grippers";
    }

    @GetMapping("/fxcb-fmcb")
    public String ecoSystem56() {
        return "eco/ecosystem_template_56_fxcb_fmcb";
    }

    @GetMapping("/cobot-pump-ecbpi")
    public String ecoSystem57() {
        return "eco/ecosystem_template_57_Cobot-Pump-ECBPi";
    }

    @GetMapping("/jpgb-3285")
    public String ecoSystem58() {
        return "eco/ecosystem_template_58_jpgb_3285";
    }

}
