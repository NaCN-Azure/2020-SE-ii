package com.example.hotel.controller.saler;



import com.example.hotel.bl.user.AccountService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.hotel.bl.saler.SalerService;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/saler")
public class SalerController {
    @Autowired
    private SalerService salerService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/webAllCoupons")
    public ResponseVO getAllSalerCoupons(){
        return ResponseVO.buildSuccess(salerService.getWebAllCoupon());
    }


    @PostMapping("/addWebCoupon")
    public ResponseVO addWebCoupon(@RequestBody SalerCouponVO salerCouponVO) {
        SalerCouponVO salercouponVO = salerService.addWebCoupon(salerCouponVO);
        return ResponseVO.buildSuccess(salercouponVO);
    }

    @PostMapping("/updateWebCoupon")
    public ResponseVO updateWebCoupon(@RequestBody SalerCouponVO salercouponVO) {
        System.out.println(salercouponVO.getName());
        salerService.updateSalerCouponInfo(salercouponVO);
        return ResponseVO.buildSuccess(true);
    }
    @PostMapping("/addCreditById")
    public ResponseVO addCreditByid(@RequestBody UserVO userVO) {
        System.out.println(userVO.getId());
        accountService.addCredit(userVO);
        return ResponseVO.buildSuccess(true);
    }


    @GetMapping("/getFileList")
    public ResponseVO getFile ()
    {
        return ResponseVO.buildSuccess(salerService.getAD());
    }

    @PostMapping("/addAD")
    public ResponseVO addAD(@RequestBody ADVO adVO) {
        salerService.addAD(adVO);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/deleteWebCoupon")
    public ResponseVO deleteWebCoupon(@RequestParam Integer CouponId){
        salerService.deleteWebCoupon(CouponId);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/getWebCouponById")
    public ResponseVO getWebCouponById(@RequestParam Integer webCouponId){
        return ResponseVO.buildSuccess(salerService.getWebCouponById(webCouponId));
    }

}
