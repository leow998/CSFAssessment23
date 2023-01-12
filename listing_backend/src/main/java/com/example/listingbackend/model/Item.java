package com.example.listingbackend.model;

import com.example.listingbackend.dto.ItemDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    private String product_id;

    private Date posting_date;

    private String name;

    private String email;

    private String phone;

    private String title;

    private String description;

    @Lob
    private byte[] img;

    public void getItemEntity(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.email = itemDto.getEmail();
        this.phone = itemDto.getPhone();
        this.title = itemDto.getTitle();
        this.description = itemDto.getDescription();
    }

    public ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setProduct_id(product_id);
        itemDto.setName(name);
        itemDto.setEmail(email);
        itemDto.setPhone(phone);
        itemDto.setTitle(title);
        itemDto.setDescription(description);
        itemDto.setPosting_date(posting_date);
        itemDto.setReturnedImg(img);
        return itemDto;
    }
}
