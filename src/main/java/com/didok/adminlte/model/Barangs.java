package com.didok.adminlte.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Barangs extends AbstractModel<Long> {

    @Column(nullable = false, length = 100, unique = true)
    private String nama;

    @Column(nullable = false)
    private Double harga;

    @Column(nullable = false)
    private Double stock;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date date;
}
