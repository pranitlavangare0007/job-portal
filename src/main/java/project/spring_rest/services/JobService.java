package project.spring_rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring_rest.models.JobPost;
import project.spring_rest.repositry.Repo;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    public Repo repo;

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public JobPost addJobPost(JobPost jobPost) {
        return repo.save(jobPost);
    }

    public Optional<JobPost> getJob(int i) {
        return repo.findById(i);
    }

    public void updateJob(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deleteJob(int postId) {

        repo.deleteById(postId);
    }

    public List<JobPost> getJobsByKey(String key) {

        return repo.findByPostProfileContainingOrPostDescContaining(key,key);
    }
}