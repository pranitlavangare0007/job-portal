package project.spring_rest.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring_rest.models.Users;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
