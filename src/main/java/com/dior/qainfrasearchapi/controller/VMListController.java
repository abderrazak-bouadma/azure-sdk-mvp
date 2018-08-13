package com.dior.qainfrasearchapi.controller;

import com.dior.qainfrasearchapi.service.QAVmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Abderrazak BOUADMA
 * on 13/08/2018.
 */
@RestController
@RequestMapping("/api/v1/vms")
public class VMListController {

    private final QAVmService qaVmService;

    @Autowired
    public VMListController(QAVmService qaVmService) {
        this.qaVmService = qaVmService;
    }

    @GetMapping
    ResponseEntity<List<VMStateDto>> getAvailableQAVms() throws IOException {
        return ResponseEntity.ok(qaVmService.getAvailableQAVirtualMachines());
    }

    @PutMapping("/{id}")
    void toggleAvailability(@PathVariable String id) {
        qaVmService.setVMUnavailable(id);
    }
}
