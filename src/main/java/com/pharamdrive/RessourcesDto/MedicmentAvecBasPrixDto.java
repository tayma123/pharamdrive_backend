package com.pharamdrive.RessourcesDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicmentAvecBasPrixDto {
    String idPharmacie;
    String nomPharmacie;
    String nomMedicament;
    String idMedicament;
    String prix;
}
