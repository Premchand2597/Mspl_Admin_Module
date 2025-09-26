package com.example.mspl_connect.AdminEntity;

//Data transfer object (DTO) for incoming requests
public class AssetUpdateRequest {
 private Integer id; // Asset ID
 private String availableDate; // Available date

 // Getters and setters
 public Integer getId() {
     return id;
 }

 public void setId(Integer id) {
     this.id = id;
 }

 public String getAvailableDate() {
     return availableDate;
 }

 public void setAvailableDate(String availableDate) {
     this.availableDate = availableDate;
 }
}