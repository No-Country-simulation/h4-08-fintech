package com.web.backend.domain.model.asset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE asset SET deleted = true WHERE id = ?")
@SQLDelete(sql = "deleted=true")
public class Asset {

    @Id
    private String ticker;
    private Float price;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
}
