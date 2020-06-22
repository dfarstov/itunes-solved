package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.utils.DateUtils;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createdAt;
    private Long updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = DateUtils.nowUnix();

        if (this.updatedAt == null)
            this.updatedAt = DateUtils.nowUnix();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = DateUtils.nowUnix();
    }
}
