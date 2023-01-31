package com.didok.adminlte.repository;

import com.didok.adminlte.model.Perusahaans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerusahaansRepository extends JpaRepository<Perusahaans, Long> {

}
