package com.nakulsiwach.ecommproj.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class APIResponse {
    public String message;
    public boolean status;
}
