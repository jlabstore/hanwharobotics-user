package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.RobotBoardType;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.robot.RobotCategoryResponse;
import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import com.hanwha.robotics.user.service.RobotCategoryService;
import com.hanwha.robotics.user.service.RobotService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agv-amr")
@RequiredArgsConstructor
@Slf4j
public class AgvAmrController {

    private final RobotCategoryService robotCategoryService;

    private final RobotService robotService;

    private final CommonUtil commonUtil;

    @GetMapping
    public String products() {
        return "agv_amr/products";
    }

    @GetMapping("/case-studies")
    public String caseStudiesListPage(Model model) {
        List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory2(RobotBoardType.ROBOT_CASE.name());
        model.addAttribute("robotCategory", robotCategory);
        return "agv_amr/case_studies_list";
    }

    @PostMapping("/case-studies/category")
    public ResponseEntity<Object> findCaseByCategory(
        RobotRequest robotRequest,
        HttpServletRequest request
    ) {
        String lang = commonUtil.getCookieLang(request);
        robotRequest.setLang(lang);
        List<RobotResponse> robotResponseList = robotService.findRobotByCategory(robotRequest);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), robotResponseList));
    }



    @PostMapping("/case-studies")
    public ResponseEntity<Object> findCaseByCategory(
        RobotRequest robotRequest,
        PageRequest pageRequest,
        HttpServletRequest request
    ) {
        String lang = commonUtil.getCookieLang(request);
        robotRequest.setLang(lang);
//    List<RobotResponse> robotResponseList = robotService.findRobotByCategory(robotRequest);
        PageResponse body = robotService.findRobotByCategory2(robotRequest, pageRequest, lang);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), body));
    }



    @GetMapping("/case-studies/{robotNo}")
    public String caseStudiesViewPage(@PathVariable int robotNo, Model model) {
        log.info("상세페이지");
        List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory2(RobotBoardType.ROBOT_CASE.name());
        RobotResponse robotResponse = robotService.findRobotByRobotNo(robotNo);
        List<RobotFileResponse> robotFileList = robotService.findRobotFileByRoboyNoNoneThumnail(robotNo);
        model.addAttribute("robotCategory", robotCategory);
        model.addAttribute("robotResponse", robotResponse);
        model.addAttribute("robotFileList", robotFileList);
        return "agv_amr/case_studies_view";
    }



    @GetMapping("/forklift-type")
    public String forklift() {
        return "agv_amr/products_detail_forklift";
    }

    @GetMapping("/reel-and-roll-type")
    public String reelAndRoll() {
        return "agv_amr/products_detail_reel_roll";
    }

    @GetMapping("/pallet-truck-type")
    public String palletTruck() {
        return "agv_amr/products_detail_pallet_truck";
    }

    @GetMapping("/high-mast-type")
    public String highMast() {
        return "agv_amr/products_detail_high_mast";
    }

    @GetMapping("/conveyor-type")
    public String conveyor() {
        return "agv_amr/products_detail_conveyor";
    }
    
    @GetMapping("/glass-packaging-type")
    public String semiconductor() {
        return "agv_amr/products_detail_semiconductor";
    }

    @GetMapping("/wafer-production-type")
    public String waferProduction1() {
        return "agv_amr/products_detail_wafer_production1";
    }

    @GetMapping("/semiconductor-production-type")
    public String waferProduction2() {
        return "agv_amr/products_detail_wafer_production2";
    }

    @GetMapping("/amr")
    public String amr() {
        return "agv_amr/products_detail_amr";
    }


}
