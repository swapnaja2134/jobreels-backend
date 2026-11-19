package com.jobreels.jobreels_backend.controller;

import com.jobreels.jobreels_backend.model.*;
import com.jobreels.jobreels_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    @Autowired private UserRepository userRepo;
    @Autowired private JobRepository jobRepo;
    @Autowired private ReelRepository reelRepo;
    @Autowired private ApplicationRepository appRepo;
    @Autowired private MessageRepository msgRepo;
    @Autowired private ReportRepository reportRepo;

    @PostMapping("/auth/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> payload) {
        String mobile = payload.get("mobile");
        String otp = payload.get("otp");
        String role = payload.getOrDefault("role", "CANDIDATE");

        if (!"1234".equals(otp)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid OTP. Use 1234 for demo."));
        }

        User user = userRepo.findByMobile(mobile).orElseGet(() -> {
            User newUser = new User();
            newUser.setMobile(mobile);
            newUser.setRole(role);
            newUser.setName(role.equals("EMPLOYER") ? "New HR Partner" : "New Candidate");
            return userRepo.save(newUser);
        });

        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public User updateProfile(@PathVariable Long id, @RequestBody User updated) {
        User existing = userRepo.findById(id).orElseThrow();
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
        if (updated.getWorkCategory() != null) existing.setWorkCategory(updated.getWorkCategory());
        if (updated.getEducation() != null) existing.setEducation(updated.getEducation());
        if (updated.getExperience() != null) existing.setExperience(updated.getExperience());
        if (updated.getSkills() != null) existing.setSkills(updated.getSkills());
        if (updated.getPreferredLocation() != null) existing.setPreferredLocation(updated.getPreferredLocation());
        if (updated.getExpectedSalary() != null) existing.setExpectedSalary(updated.getExpectedSalary());
        if (updated.getLanguage() != null) existing.setLanguage(updated.getLanguage());
        if (updated.getCompanyName() != null) existing.setCompanyName(updated.getCompanyName());
        if (updated.getCompanyType() != null) existing.setCompanyType(updated.getCompanyType());
        if (updated.getHrName() != null) existing.setHrName(updated.getHrName());
        if (updated.getCompanyAbout() != null) existing.setCompanyAbout(updated.getCompanyAbout());
        return userRepo.save(existing);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/reels")
    public List<Reel> getReels() {
        return reelRepo.findByRemovedFalse();
    }

    @PostMapping("/reels")
    public Reel createReel(@RequestBody Reel reel) {
        return reelRepo.save(reel);
    }

    @PostMapping("/reels/{id}/like")
    public Reel likeReel(@PathVariable Long id) {
        Reel reel = reelRepo.findById(id).orElseThrow();
        reel.setLikesCount(reel.getLikesCount() + 1);
        return reelRepo.save(reel);
    }

    @GetMapping("/jobs")
    public List<Job> getJobs() {
        return jobRepo.findByStatusOrderByPostedAtDesc("ACTIVE");
    }

    @PostMapping("/jobs")
    public Job createJob(@RequestBody Job job) {
        return jobRepo.save(job);
    }

    @PostMapping("/applications")
    public Application applyJob(@RequestBody Application app) {
        return appRepo.save(app);
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications() {
        return appRepo.findAll();
    }

    @PutMapping("/applications/{id}/status")
    public Application updateApplicationStatus(@PathVariable Long id, @RequestBody Application details) {
        Application app = appRepo.findById(id).orElseThrow();
        if (details.getStatus() != null) app.setStatus(details.getStatus());
        if (details.getInterviewDate() != null) app.setInterviewDate(details.getInterviewDate());
        if (details.getInterviewTime() != null) app.setInterviewTime(details.getInterviewTime());
        if (details.getInterviewType() != null) app.setInterviewType(details.getInterviewType());
        if (details.getInterviewAddress() != null) app.setInterviewAddress(details.getInterviewAddress());
        if (details.getContactPerson() != null) app.setContactPerson(details.getContactPerson());
        if (details.getContactNumber() != null) app.setContactNumber(details.getContactNumber());
        return appRepo.save(app);
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return msgRepo.findAllByOrderBySentAtAsc();
    }

    @PostMapping("/messages")
    public Message sendMessage(@RequestBody Message msg) {
        return msgRepo.save(msg);
    }

    @GetMapping("/reports")
    public List<Report> getReports() {
        return reportRepo.findAll();
    }

    @PostMapping("/reports")
    public Report createReport(@RequestBody Report report) {
        return reportRepo.save(report);
    }

    @PutMapping("/admin/users/{id}/toggle-block")
    public User toggleBlockUser(@PathVariable Long id) {
        User u = userRepo.findById(id).orElseThrow();
        u.setBlocked(!u.isBlocked());
        return userRepo.save(u);
    }

    @PutMapping("/admin/users/{id}/verify")
    public User verifyCompany(@PathVariable Long id) {
        User u = userRepo.findById(id).orElseThrow();
        u.setVerified(true);
        return userRepo.save(u);
    }

    @DeleteMapping("/admin/jobs/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobRepo.deleteById(id);
    }

    @DeleteMapping("/admin/reels/{id}")
    public void deleteReel(@PathVariable Long id) {
        Reel r = reelRepo.findById(id).orElseThrow();
        r.setRemoved(true);
        reelRepo.save(r);
    }
}