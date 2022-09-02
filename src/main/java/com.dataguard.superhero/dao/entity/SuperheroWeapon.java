package com.dataguard.superhero.dao.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@AllArgsConstructor
@Table(name = "superhero_weapons")
@Entity
public class SuperheroWeapon extends BaseAttribute{
}
