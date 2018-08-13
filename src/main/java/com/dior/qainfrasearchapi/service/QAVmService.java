package com.dior.qainfrasearchapi.service;

import com.dior.qainfrasearchapi.controller.VMStateDto;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.VirtualMachine;
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

    public QAVmService() throws IOException {
    }

    public List<VMStateDto> getAvailableQAVirtualMachines() {

        //final Azure azure = Azure.authenticate(AzureCliCredentials.create()).withDefaultSubscription();

        PagedList<VirtualMachine> vms = azure.virtualMachines().listByResourceGroup("QA-DIOR");

        return vms.stream()
                .filter(this::isQAAndAvailable)
                .map(vm -> new VMStateDto(vm.tags().get("id"), vm.getPrimaryPublicIPAddress().ipAddress()))
                .collect(Collectors.toList());
    }

    private boolean isQAAndAvailable(VirtualMachine virtualMachine) {
        Map<String, String> tags = virtualMachine.tags();
        return tags.get("scope").equals("qa") && tags.keySet().contains("id") && tags.get("isAvailable").equals("1");
    }

    public void setVMUnavailable(String id) {

        //final Azure azure = Azure.authenticate(AzureCliCredentials.create()).withDefaultSubscription();

        PagedList<VirtualMachine> vms = azure.virtualMachines().listByResourceGroup("QA-DIOR");

        List<VirtualMachine> virtualMachines = vms.stream()
                .filter(vm -> vm.tags().get("id").equals(id))
                .collect(Collectors.toList());

        if (virtualMachines.size() == 1) {
            VirtualMachine virtualMachine = virtualMachines.get(0);
            if (virtualMachine.tags().get("isAvailable").equals("1")) {
                virtualMachine.update().withTag("isAvailable", "0").apply();
            } else {
                virtualMachine.update().withTag("isAvailable", "1").apply();
            }
        }

    }
}
