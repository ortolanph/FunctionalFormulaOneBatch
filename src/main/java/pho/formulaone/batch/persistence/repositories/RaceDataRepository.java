package pho.formulaone.batch.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pho.formulaone.batch.persistence.entities.RaceData;

import java.util.List;

@Repository
public interface RaceDataRepository extends JpaRepository<RaceData, Long> {

    List<RaceData> findAllByStatusContaining(String status);

}
