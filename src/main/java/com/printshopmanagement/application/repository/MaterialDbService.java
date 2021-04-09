package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MaterialDbService {
    private MaterialRepo materialRepo;

    public Material saveMaterial(final Material material) {
        return materialRepo.save(material);
    }

    public Optional<Material> getMaterial(final Long id) {
        return materialRepo.findById(id);
    }

    public void deleteMaterial(final Long id) {
        materialRepo.deleteById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepo.findAll();
    }
}
