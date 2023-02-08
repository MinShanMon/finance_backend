// package com.personalfinance.backend.model;

// import java.util.ArrayList;
// import java.util.List;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.validation.constraints.NotBlank;

// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// public class RegUser {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;
//     @NotBlank
//     private String username;
//     @NotBlank
//     private String password;
//     @NotBlank
//     private String email;
//     @NotBlank
//     private String fullname;

//     @OneToMany (mappedBy = "user")
//     private List<Transaction> transactions;

//     public RegUser(String username, String password, String email, String fullname) {
//         this.username = username;
//         this.password = password;
//         this.email = email;
//         this.fullname = fullname;
//         this.transactions = new ArrayList<>();
//     }
// }
