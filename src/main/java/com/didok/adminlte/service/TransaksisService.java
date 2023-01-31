package com.didok.adminlte.service;

import com.didok.adminlte.model.Transaksis;
import com.didok.adminlte.repository.TransaksisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TransaksisService extends AbstractService<Transaksis, Long> {

    @Autowired
    private TransaksisRepository transaksisRepository;

    @Override
    protected JpaRepository<Transaksis, Long> getRepository() {
        return transaksisRepository;
    }

}
