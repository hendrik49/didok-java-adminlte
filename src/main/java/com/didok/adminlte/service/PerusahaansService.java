package com.didok.adminlte.service;

import com.didok.adminlte.model.Perusahaans;
import com.didok.adminlte.repository.PerusahaansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PerusahaansService extends AbstractService<Perusahaans, Long> {

    @Autowired
    private PerusahaansRepository perusahaansRepository;

    @Override
    protected JpaRepository<Perusahaans, Long> getRepository() {
        return  perusahaansRepository;
    }

}
