package dz.locationvoiture.domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "voiture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID reference;
    @Enumerated(EnumType.STRING)
    private Marque marque;
    private String model;
    private String anneeDeCirculation;
    private String couleur;
    private String immatriculation;
    private BigDecimal prixDeLocation;
    private Integer kilometrage;

}
