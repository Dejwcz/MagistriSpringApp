package cz.vsb.magistri.repository;

import cz.vsb.magistri.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Integer> {
}
