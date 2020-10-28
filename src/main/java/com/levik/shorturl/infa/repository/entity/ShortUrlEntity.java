package com.levik.shorturl.infa.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "short_url")
@Table(name = "short_url")
public class ShortUrlEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "short_url_id_sequence")
    @GenericGenerator(
            name = "short_url_id_sequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "short_url_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "15"),
                    @Parameter(name = "optimizer", value = "hilo")
            })
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "long_url", nullable = false)
    private String longUrl;
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void updateExpiredAtTo(int year) {
        this.expiredAt = LocalDateTime.now().plusYears(year);
    }
}
