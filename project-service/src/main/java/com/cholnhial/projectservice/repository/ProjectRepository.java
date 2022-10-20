package com.cholnhial.projectservice.repository;

import com.cholnhial.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(Long userId);

    @Query("""
select p from Project p
left join fetch p.collaborators
left join fetch p.artefact
 where p.id not in (
    select p2.id from Project  p2
    join p2.collaborators as col
    where col.userId = :userId
) and p.userId <> :userId
""")
    List<Project> findAllNotOwnedByUserId(@Param("userId") Long userId);

    @Query("""
select p from Project p where p.id IN  (
    select p2.id from Project p2 
    left join p2.collaborators c
    WHERE c.userId = :userId
)
""")
    List<Project> findAllCollaboratingInByUserId(@Param("userId") Long userId);
}
