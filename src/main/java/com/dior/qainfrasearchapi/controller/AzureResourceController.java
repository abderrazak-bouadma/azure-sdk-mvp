package com.dior.qainfrasearchapi.controller;

import com.dior.qainfrasearchapi.controller.dtos.VirtualMachineDto;
import com.dior.qainfrasearchapi.service.QAVmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Simple REST Controller which provide reading state of an Azure resource
 *
 */
@RestController
@RequestMapping("/env")
public class AzureResourceController {

    private final QAVmService qaVmService;

    @Autowired
    public AzureResourceController(QAVmService qaVmService) {
        this.qaVmService = qaVmService;
    }

    @GetMapping("/vms")
    ResponseEntity<List<VirtualMachineDto>> getAvailableQAVms() {
        return ResponseEntity.ok(qaVmService.getAvailableQAVirtualMachines());
    }

}
