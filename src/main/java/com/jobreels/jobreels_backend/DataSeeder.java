package com.jobreels.jobreels_backend;

import com.jobreels.jobreels_backend.model.*;
import com.jobreels.jobreels_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final JobRepository jobRepo;
    private final ReelRepository reelRepo;

    public DataSeeder(UserRepository userRepo, JobRepository jobRepo, ReelRepository reelRepo) {
        this.userRepo = userRepo;
        this.jobRepo = jobRepo;
        this.reelRepo = reelRepo;
    }

    @Override
    public void run(String... args) {
        if (userRepo.count() > 0) return;

        User candidate = new User();
        candidate.setMobile("9876543210");
        candidate.setName("Rohan Deshmukh");
        candidate.setRole("CANDIDATE");
        candidate.setLocation("Baner, Pune");
        candidate.setWorkCategory("Software & IT");
        candidate.setEducation("Graduate");
        candidate.setExperience("Fresher");
        candidate.setSkills("Java, Android SDK, REST APIs, MySQL");
        candidate.setPreferredLocation("Pune");
        candidate.setExpectedSalary("3.5 LPA");
        candidate = userRepo.save(candidate);

        User employer = new User();
        employer.setMobile("9123456780");
        employer.setName("Priya Kulkarni (HR)");
        employer.setRole("EMPLOYER");
        employer.setVerified(true);
        employer.setCompanyName("TechNova Solutions Pvt Ltd");
        employer.setCompanyType("IT & Software");
        employer.setLocation("Baner, Pune");
        employer.setHrName("Priya Kulkarni");
        employer.setCompanyAbout("Fast-growing mobile and web product company in Pune.");
        employer = userRepo.save(employer);

        User admin = new User();
        admin.setMobile("9000000000");
        admin.setName("Platform Admin");
        admin.setRole("ADMIN");
        userRepo.save(admin);

        Job j1 = new Job();
        j1.setEmployerId(employer.getId());
        j1.setCompanyName("TechNova Solutions Pvt Ltd");
        j1.setCompanyVerified(true);
        j1.setTitle("Junior Android Developer");
        j1.setCategory("IT & Software");
        j1.setSalary("₹3.5 LPA");
        j1.setLocation("Baner, Pune");
        j1.setExperienceRequired("Fresher");
        j1.setEducationRequired("Graduate");
        j1.setVacancies(3);
        j1.setJobType("Full Time");
        j1.setWorkingHours("10:00 AM - 7:00 PM");
        j1.setDescription("Build and maintain high-performance Android apps using Java, XML, and REST APIs.");
        j1 = jobRepo.save(j1);

        Job j2 = new Job();
        j2.setEmployerId(employer.getId());
        j2.setCompanyName("Chakan Precision Engineering");
        j2.setCompanyVerified(true);
        j2.setTitle("CNC Machine Operator & Electrician");
        j2.setCategory("Manufacturing");
        j2.setSalary("₹22,000 / month");
        j2.setLocation("Chakan MIDC, Pune");
        j2.setExperienceRequired("0-1 Year");
        j2.setEducationRequired("ITI / Diploma");
        j2.setVacancies(5);
        j2.setJobType("Full Time");
        j2.setWorkingHours("8:00 AM - 5:00 PM");
        j2.setDescription("Operate CNC machinery and handle industrial electrical maintenance.");
        jobRepo.save(j2);

        Reel r1 = new Reel();
        r1.setUserId(candidate.getId());
        r1.setCreatorName("Rohan Deshmukh");
        r1.setCreatorRole("CANDIDATE");
        r1.setTitle("My Android App UI & REST API Demo #Java #Android");
        r1.setCategory("Software");
        r1.setVideoUrl("https://assets.mixkit.co/videos/preview/mixkit-man-working-on-his-laptop-308-large.mp4");
        r1.setLikesCount(24);
        reelRepo.save(r1);

        Reel r2 = new Reel();
        r2.setUserId(employer.getId());
        r2.setCreatorName("TechNova Solutions Pvt Ltd");
        r2.setCreatorRole("EMPLOYER");
        r2.setTitle("We are hiring Junior Android Developers in Baner! Apply Now");
        r2.setCategory("Hiring");
        r2.setVideoUrl("https://assets.mixkit.co/videos/preview/mixkit-hands-of-a-man-playing-on-a-computer-43527-large.mp4");
        r2.setLinkedJobId(j1.getId());
        r2.setLikesCount(42);
        reelRepo.save(r2);

        Reel r3 = new Reel();
        r3.setUserId(candidate.getId());
        r3.setCreatorName("Amit Patil (ITI Electrician)");
        r3.setCreatorRole("CANDIDATE");
        r3.setTitle("Industrial Panel Wiring & Maintenance Demo");
        r3.setCategory("Electrical");
        r3.setVideoUrl("https://assets.mixkit.co/videos/preview/mixkit-electrician-working-on-an-electrical-panel-43522-large.mp4");
        r3.setLikesCount(19);
        reelRepo.save(r3);
    }
}