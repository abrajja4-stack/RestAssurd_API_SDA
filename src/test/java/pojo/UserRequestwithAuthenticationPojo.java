package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//This will ignore the properties that does not exist in this class while converting response into this class object.
public class UserRequestwithAuthenticationPojo {

    private String name;
    private String job;

    public UserRequestwithAuthenticationPojo() {
    }


    public UserRequestwithAuthenticationPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}



