package com.ed.timemanager.commons.models;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class BaseModel {
    //region Fields

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Version
    private long version;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    //endregion
    //region Public

    @Override
    public String toString() {

        return String.format("%s (%s)", this.getClass().getName(), this.getId());
    }

    @PrePersist
    public void prePersist() {
        
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        
        this.modifiedDate = Instant.now();
    }

    //endregion
}
