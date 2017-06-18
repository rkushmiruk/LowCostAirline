package com.kushmiruk.model.entity;

import java.io.Serializable;

/**
 * Base persisted entity
 */
public abstract class Entity implements Serializable {

    /**
     * @return entity id
     */
    protected abstract Long getId();

    /**
     * sets entity id
     *
     * @param id given id
     */
    protected abstract void setId(Long id);
}
