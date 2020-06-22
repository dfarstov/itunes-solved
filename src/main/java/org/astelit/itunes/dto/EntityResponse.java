package org.astelit.itunes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.entity.BaseEntity;

@Getter
@Setter
public abstract class EntityResponse {
    private final Long id;
    private final Long createdAt;
    private final Long updatedAt;

    public EntityResponse(BaseEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
