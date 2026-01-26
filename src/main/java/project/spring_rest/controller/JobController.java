package project.spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import project.spring_rest.models.JobPost;

import project.spring_rest.services.JobService;


import java.util.List;
import java.util.Optional;

@RestController

public class JobController {

    @Autowired
    JobService services;

    @GetMapping("/jobs")
    public List<JobPost> getAllJobs(){
        return services.getAllJobs();
    }



    @GetMapping("/jobs/keyword/{keyword}")
    public List<JobPost> getJobsByKeyWord(@PathVariable("keyword") String key){

        return services.getJobsByKey(key);
    }


    @GetMapping("/job/{postId}")
    public Optional<JobPost> getJob(@PathVariable int postId){
        return services.getJob(postId);
    }


    @PostMapping("/job")
    public JobPost addJobs(@RequestBody JobPost jobPost){

        return services.addJobPost(jobPost);
    }
    @PutMapping("/job")
    public Optional<JobPost> updateJob(@RequestBody JobPost jobPost){
        services.updateJob(jobPost);
        return services.getJob(jobPost.getPostId());
    }

    @DeleteMapping("/job/{postId}")
    public String deleteJob(@PathVariable int postId){
        services.deleteJob(postId);
        return "Deleted";
    }

}
