package com.pundix.entity.list;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list")
public class List {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;
}
