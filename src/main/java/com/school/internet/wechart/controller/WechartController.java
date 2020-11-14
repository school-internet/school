package com.school.internet.wechart.controller;


import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("wechart")
public class WechartController {

    @Autowired
    private  WxMpService wxService;


    private static final String REDIRECT_ULR = "school.harvestcloud.cn/wxindex";


    @GetMapping ("/auth")
    public void auth(HttpServletResponse response) throws IOException {
        System.out.print("code ="+wxService.oauth2buildAuthorizationUrl(REDIRECT_ULR, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null));
        response.sendRedirect(wxService.oauth2buildAuthorizationUrl(REDIRECT_ULR, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null));
    }



}
