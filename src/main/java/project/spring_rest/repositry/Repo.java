package project.spring_rest.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring_rest.models.JobPost;

import java.util.List;

@Repository
public interface Repo extends JpaRepository<JobPost,Integer> {

List<JobPost> findByPostProfileContainingOrPostDescContaining(String profile, String des);
}
