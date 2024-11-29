package com.example.task_management_system.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;
    private String title;
    @Lob
    private String Description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;  // This is the foreign key to User

}
