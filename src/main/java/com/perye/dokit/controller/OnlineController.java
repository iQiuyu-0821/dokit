package com.perye.dokit.controller;

import com.perye.dokit.aop.log.Log;
import com.perye.dokit.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/online")
@Api(tags = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    public OnlineController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Log("查询在线用户")
    @ApiOperation("查询在线用户")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAll(String filter, Pageable pageable){
        return new ResponseEntity<>(onlineUserService.getAll(filter, pageable), HttpStatus.OK);
    }

    @Log("踢出用户")
    @ApiOperation("踢出用户")
    @DeleteMapping(value = "/{key}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String key) throws Exception {
        onlineUserService.kickOut(key);
        return new ResponseEntity(HttpStatus.OK);
    }
}
