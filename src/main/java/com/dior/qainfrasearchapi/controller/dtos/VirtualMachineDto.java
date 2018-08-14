package com.dior.qainfrasearchapi.controller.dtos;

import lombok.Data;

/**
 * Created by Abderrazak BOUADMA
 * on 09/08/2018.
 */
@Data
public class VirtualMachineDto {

    private String id;
    private String publicIp;
    private String login;
    private String password;
}
