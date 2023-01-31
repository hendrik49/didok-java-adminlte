package com.didok.adminlte.service;

import com.didok.adminlte.model.Barangs;
import com.didok.adminlte.repository.BarangsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BarangsService extends AbstractService<Barangs, Long> {

    @Autowired
    private BarangsRepository barangsRepository;

    @Override
    protected JpaRepository<Barangs, Long> getRepository() {
        return barangsRepository;
    }

}
