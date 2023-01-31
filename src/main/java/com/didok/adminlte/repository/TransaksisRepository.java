package com.didok.adminlte.repository;

import com.didok.adminlte.model.Transaksis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksisRepository extends JpaRepository<Transaksis, Long> {

}
