package com.Finance.Dashboard.Model;


import com.Finance.Dashboard.Model.Enum.RecordType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.Relational;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinRecords  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Positive
    private Double amount;

    @Enumerated(EnumType.STRING) @NotNull
    private RecordType type;

    @NotBlank
    private String category;
    @NotNull
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // Constructor
public FinRecords(Double amount, RecordType type, String category, LocalDate date,
                  String description,User user){
    this.amount = amount;
    this.type = type;
    this.category = category;
    this.date = date;
    this.description = description;
    this.user = user;
}
}
