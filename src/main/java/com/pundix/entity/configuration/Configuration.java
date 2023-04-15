package com.pundix.entity.configuration;

import com.pundix.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuration")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_configuration")
public class Configuration extends BaseEntity {

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
