package common.transferObjects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class JobAd implements Serializable {
    private int jobId;
    private String jobTitle;
    private User company;
    private String jobDescription;
    private ArrayList<String>requirements;


    private ArrayList<Applicant> applicants;


    public JobAd(String jobTitle, User company, String jobDescription, ArrayList<String> requirements) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
        applicants = new ArrayList<>();
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public JobAd(int id, String jobTitle, User company, String jobDescription, ArrayList<String> requirements, ArrayList<Applicant> applicants) {
        jobId = id;
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
        this.applicants = applicants;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setApplicants(ArrayList<Applicant> applicants) {
        this.applicants = applicants;
    }

    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User getCompany() {
        return company;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public String getRequirementsForDB(){
        String result = "ARRAY [";
        if(requirements.size() == 0){
            return "NULL";
        }else{
            for(int i = 0; i<requirements.size(); i++){
                result +="'" + requirements.get(i) + "'";
                if(i+1 < requirements.size()){
                    result += ", ";
                }
            }
            result += "]";
            return result;
        }
    }
    public String getApplicantsForDB(){
        String result = "ARRAY [";
        if(applicants.size() == 0){
            return "NULL";
        }else{
            for(int i = 0; i<applicants.size(); i++){
                result +="'" + applicants.get(i).getUsername() + "'";
                if(i+1 < applicants.size()){
                    result += ", ";
                }
            }
            result += "]";
            return result;
        }
    }

    public String toString(){
        return jobTitle + "::" + company.getFullName() + "::" + jobDescription + "::" + applicants.toString();
    }

    public void addApplicant(Applicant newApplicant){
        if(!alreadyApplied(newApplicant)){
            applicants.add(newApplicant);
        }
    }
    public boolean alreadyApplied(Applicant newApplicant){
        boolean alreadyApplied = false;
        for(int i = 0; i < applicants.size(); i++){
            if(applicants.get(i).getUsername().equals(newApplicant.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobAd jobAd = (JobAd) o;
        return jobId == jobAd.jobId && Objects.equals(jobTitle, jobAd.jobTitle) && Objects.equals(company, jobAd.company) && Objects.equals(jobDescription, jobAd.jobDescription) && Objects.equals(requirements, jobAd.requirements) && Objects.equals(applicants, jobAd.applicants);
    }

}
