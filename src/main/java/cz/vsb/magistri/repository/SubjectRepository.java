package cz.vsb.magistri.repository;

import cz.vsb.magistri.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
}
