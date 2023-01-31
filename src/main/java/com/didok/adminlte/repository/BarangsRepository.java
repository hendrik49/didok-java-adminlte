package com.didok.adminlte.repository;

import com.didok.adminlte.model.Barangs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangsRepository extends JpaRepository<Barangs, Long> {

}
