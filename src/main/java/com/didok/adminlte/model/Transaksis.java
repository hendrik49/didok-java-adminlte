package com.didok.adminlte.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaksis extends AbstractModel<Long> {

    @Column(name = "id_perusahaan", nullable = false)
    private Long id_perusahaan;

    @OneToOne(optional=false)
    @JoinColumn(name = "id_perusahaan", insertable=false, updatable=false)
    private Perusahaans perusahaan;

    @Column(name = "id_barang", nullable=false)
    private Long id_barang;

    @OneToOne(optional=false)
    @JoinColumn(name = "id_barang", insertable=false, updatable=false)
    private Barangs barang;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Double grand_total;

    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date date;
}
