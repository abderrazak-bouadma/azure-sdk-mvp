package com.dior.qainfrasearchapi.controller;

import com.dior.qainfrasearchapi.domain.VMState;
import com.dior.qainfrasearchapi.repository.VMStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Abderrazak BOUADMA
 * on 07/08/2018.
 */
@RestController
@RequestMapping("/environments")
public class OnDemandVMStateController {

    private final VMStateRepository vmStateRepository;

    @Autowired
    public OnDemandVMStateController(VMStateRepository vmStateRepository) {
        this.vmStateRepository = vmStateRepository;
    }

    @GetMapping
    ResponseEntity<List<VMState>> getAllVMStates() {
        List<VMState> result = new ArrayList<>();
        Iterable<VMState> all = vmStateRepository.findAll();
        all.forEach(result::add);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Transactional
    ResponseEntity<VMState> registerEnvironment(@RequestBody VMState state) {
        VMState saved = vmStateRepository.save(state);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{uid}")
    @Transactional
    void removeEnvironment(@PathVariable String uid) {
        Optional<VMState> env = vmStateRepository.findByUid(uid);
        env.ifPresent(vmState -> vmStateRepository.deleteById(vmState.getId()));
    }
}
