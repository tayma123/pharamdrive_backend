package com.tplus.PharmasDriveAPI.service;
import com.tplus.PharmasDriveAPI.model.Publicity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicityService {

    Page<Publicity> getPublicities(Pageable pageable);
    Publicity addPublicity(Publicity publicity);
    Publicity  getPublicity(String id );
    void deletePublicity(String id );
    Publicity updatePublicity(Publicity publicity);
}
