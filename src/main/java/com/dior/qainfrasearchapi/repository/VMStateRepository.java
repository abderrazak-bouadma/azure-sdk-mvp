package com.dior.qainfrasearchapi.repository;

import com.dior.qainfrasearchapi.domain.VMState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Abderrazak BOUADMA
 * on 09/08/2018.
 */
public interface VMStateRepository extends CrudRepository<VMState, Long> {

    Optional<VMState> findByUid(String uid);
}
