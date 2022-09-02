package com.dataguard.superhero.dao.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@Table(name = "superhero_assosiations")
@Entity
public class SuperheroAssociation extends BaseAttribute{

}
