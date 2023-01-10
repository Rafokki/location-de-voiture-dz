package dz.locationvoiture.dto;

import dz.locationvoiture.domaine.Marque;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class VoitureDto {
    private UUID reference;
    private Marque marque;
    private String model;
    private String anneeDeCirculation;
    private String couleur;
    private String immatriculation;
}
