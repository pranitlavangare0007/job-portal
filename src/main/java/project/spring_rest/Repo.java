package project.spring_rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface Repo extends JpaRepository<JobPost,Integer> {

List<JobPost> findByPostProfileContainingOrPostDescContaining(String profile, String des);
}
