package com.example.listingbackend.controllers;

import com.example.listingbackend.dto.ItemDto;
import com.example.listingbackend.response.GeneralResponse;
import com.example.listingbackend.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posting")
@CrossOrigin("*")
public class PostingController {

    @Autowired
    private PostingService postingService;

    @PostMapping("/item")
    public GeneralResponse createItem(@ModelAttribute ItemDto itemDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return postingService.createItem(itemDto);
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @GetMapping("/show-item/{productId}")
    public GeneralResponse getItemById(@PathVariable String productId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(postingService.getItemById(productId));
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

    @PutMapping("/update-item/{productId}")
    public GeneralResponse updateItem(@PathVariable String productId,@ModelAttribute ItemDto itemDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return (postingService.updateItem(productId,itemDto));
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something went wrong!");
            return response;
        }
    }

    @GetMapping("/allItems")
    public GeneralResponse getAllItems() {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(postingService.getAllItems());
            response.setStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Sorry Something Wrong Happened.");
            return response;
        }
    }

}
