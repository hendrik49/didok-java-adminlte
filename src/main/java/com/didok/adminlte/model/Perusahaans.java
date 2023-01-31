package com.didok.adminlte.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Perusahaans extends AbstractModel<Long> {

    @Column(nullable = false, length = 50, unique = true)
    private String kode;

    @Column(nullable = false, length = 150)
    private String nama;

    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date date;

}
