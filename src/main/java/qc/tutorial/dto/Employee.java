package qc.tutorial.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private String name;
    private String position;
    private String email;
    private String phoneNumber;
}
