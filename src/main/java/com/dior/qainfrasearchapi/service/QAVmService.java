package com.dior.qainfrasearchapi.service;

import com.dior.qainfrasearchapi.controller.dtos.VirtualMachineDto;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.VirtualMachine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Abderrazak BOUADMA
 * on 13/08/2018.
 */
@Service
public class QAVmService {

    private final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));
    private final Azure azure = Azure.configure()
            .authenticate(credFile)
            .withDefaultSubscription();
    @Value("${AZURE_RESOURCE_GROUP}")
    private String azureResourceGroup;

    public QAVmService() throws IOException {
    }

    public List<VirtualMachineDto> getAvailableQAVirtualMachines() {

        PagedList<VirtualMachine> vms = azure.virtualMachines().listByResourceGroup(azureResourceGroup);

        return vms.stream()
                .filter(this::isQAAndAvailable)
                .map(this::buildVMSDto)
                .collect(Collectors.toList());
    }

    private VirtualMachineDto buildVMSDto(VirtualMachine vm) {
        VirtualMachineDto v = new VirtualMachineDto();
        v.setId(vm.tags().get("id"));
        v.setId(vm.getPrimaryPublicIPAddress().ipAddress());
        return v;
    }

    private boolean isQAAndAvailable(VirtualMachine virtualMachine) {
        Map<String, String> tags = virtualMachine.tags();
        return tags.get("scope").equals("qa") && tags.keySet().contains("id") && tags.get("isAvailable").equals("1");
    }

}
