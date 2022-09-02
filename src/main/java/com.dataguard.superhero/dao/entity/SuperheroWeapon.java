package com.dataguard.superhero.dao.entity;

import javax.persistence.*;

import lombok.*;

@Builder
@AllArgsConstructor
@Table(name = "superhero_weapons")
@Entity
public class SuperheroWeapon extends BaseAttribute{
}
