package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    // Par requête sql :
    // UPDATE Plane p SET p.inMaintenance WHERE p.id = ?1

    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Plane p SET p.inMaintenance = ?2 WHERE p.id = ?1")
    void updateMaintenance(long idPlane, boolean maintenance);


    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Plane p SET p.company = ?2 WHERE p.id = ?1")
    void updateCompany(long idPlane, Company company);


}
