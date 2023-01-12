package com.example.listingbackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
public class ItemDto {

    private String product_id;

    private Date posting_date;

    private String name;

    private String email;

    private String phone;

    private String title;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

}
