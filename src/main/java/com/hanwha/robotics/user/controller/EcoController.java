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





    @GetMapping("/HRC-03-099507")
    public String ecoSystem28() {
        return "ecosystem_template_28";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem29() {
        return "ecosystem_template_29";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem30() {
        return "ecosystem_template_30";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem31() {
        return "ecosystem_template_31";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem32() {
        return "ecosystem_template_32";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem33() {
        return "ecosystem_template_33";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem34() {
        return "ecosystem_template_34";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem35() {
        return "ecosystem_template_35";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem36() {
        return "ecosystem_template_36";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem37() {
        return "ecosystem_template_37";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem38() {
        return "ecosystem_template_38";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem39() {
        return "ecosystem_template_39";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem40() {
        return "ecosystem_template_40";
    }

    @GetMapping("/FHS-SH-Set-HCR-Series")
    public String ecoSystem41() {
        return "ecosystem_template_41";
    }

    @GetMapping("/2F-85-2F-140")
    public String ecoSystem42() {
        return "ecosystem_template_42";
    }

    @GetMapping("/NT-50-RS-EN")
    public String ecoSystem43() {
        return "ecosystem_template_43";
    }

    @GetMapping("/AG-95-Adaptive-Gripper")
    public String ecoSystem44() {
        return "ecosystem_template_44";
    }

    @GetMapping("/NT-100-RE-EN")
    public String ecoSystem45() {
        return "ecosystem_template_45";
    }

    @GetMapping("/PHD-Pneu-Connect-X1-Pneumatic-Grippers")
    public String ecoSystem46() {
        return "ecosystem_template_46";
    }

    @GetMapping("/NT-50-CC-EN")
    public String ecoSystem47() {
        return "ecosystem_template_47";
    }

    @GetMapping("/Co-act-EGP-C")
    public String ecoSystem48() {
        return "ecosystem_template_48";
    }

    @GetMapping("/HRC-03-086003")
    public String ecoSystem49() {
        return "ecosystem_template_49";
    }

    @GetMapping("/HRC-03-099507")
    public String ecoSystem50() {
        return "ecosystem_template_50";
    }

    @GetMapping("/JEGB-4140/485")
    public String ecoSystem51() {
        return "ecosystem_template_51";
    }

    @GetMapping("/CoGrip-Drim-Robotics")
    public String ecoSystem52() {
        return "ecosystem_template_52";
    }

    @GetMapping("/CoGrip-8/4")
    public String ecoSystem53() {
        return "ecosystem_template_53";
    }

    @GetMapping("/V-Grip-System")
    public String ecoSystem54() {
        return "ecosystem_template_54";
    }

    @GetMapping("/Magic-Grippers")
    public String ecoSystem55() {
        return "ecosystem_template_55";
    }

    @GetMapping("/FXCB-FMCB")
    public String ecoSystem56() {
        return "ecosystem_template_56_FXCB-FMCB";
    }

    @GetMapping("/Cobot-Pump-ECBPi")
    public String ecoSystem57() {
        return "ecosystem_template_57_Cobot-Pump-ECBPi";
    }

    @GetMapping("/JPGB-3285/32140")
    public String ecoSystem58() {
        return "ecosystem_template_58_JPGB-3285";
    }

}
