package com.example.demo.domain.base;

import java.io.Serializable;

public interface Entity extends Serializable {
    /**
     * This interface returns the primary key of the entity.
     *
     * @return - the entity object.
     */
    Serializable getPrimaryKey();
}