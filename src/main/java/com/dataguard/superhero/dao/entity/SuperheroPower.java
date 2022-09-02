package com.dataguard.superhero.dao.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Builder
@AllArgsConstructor
@Table(name = "superhero_powers")
@Entity
public class SuperheroPower extends BaseAttribute{

}
