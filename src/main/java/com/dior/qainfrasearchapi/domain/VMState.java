package com.dior.qainfrasearchapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Abderrazak BOUADMA
 * on 09/08/2018.
 */
@Entity
@Data
@NoArgsConstructor
public class VMState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    private String publicIp;
}
