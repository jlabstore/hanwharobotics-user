package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agv-amr")
public class AgvAmrController {

    @GetMapping
    public String products() {
        return "agv_amr/products";
    }

    @GetMapping("/forklift")
    public String forklift() {
        return "agv_amr/products_detail_forklift";
    }

    @GetMapping("/reel-and-roll")
    public String reelAndRoll() {
        return "agv_amr/products_detail_reel_roll";
    }

    @GetMapping("/pallet-truck")
    public String palletTruck() {
        return "agv_amr/products_detail_pallet_truck";
    }

    @GetMapping("/high-mast")
    public String highMast() {
        return "agv_amr/products_detail_high_mast";
    }

    @GetMapping("/conveyor")
    public String conveyor() {
        return "agv_amr/products_detail_conveyor";
    }
    @GetMapping("/semiconductor")
    public String semiconductor() {
        return "agv_amr/products_detail_semiconductor";
    }

    @GetMapping("/wafer-production1")
    public String waferProduction1() {
        return "agv_amr/products_detail_wafer_production1";
    }

    @GetMapping("/wafer-production2")
    public String waferProduction2() {
        return "agv_amr/products_detail_wafer_production2";
    }

    @GetMapping("/amr")
    public String amr() {
        return "agv_amr/products_detail_amr";
    }


}
