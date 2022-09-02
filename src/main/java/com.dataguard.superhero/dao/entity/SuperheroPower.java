package com.dataguard.superhero.dao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;


@SuperBuilder
@AllArgsConstructor
@Table(name = "superhero_powers")
@Entity
public class SuperheroPower extends BaseAttribute{

}
