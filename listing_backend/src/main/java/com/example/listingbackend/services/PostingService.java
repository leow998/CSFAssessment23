package com.example.listingbackend.services;

import com.example.listingbackend.dto.ItemDto;
import com.example.listingbackend.dto.SingleItemDto;
import com.example.listingbackend.model.Item;
import com.example.listingbackend.repositories.PostingRepo;
import com.example.listingbackend.response.GeneralResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PostingService {

    @Autowired
    private PostingRepo postingRepo;

    public GeneralResponse createItem(ItemDto itemDto) throws IOException {
        GeneralResponse response = new GeneralResponse();
        try {
            Item item = new Item();
            item.getItemEntity(itemDto);
            item.setProduct_id(UUID.randomUUID().toString());
            item.setImg(itemDto.getImg().getBytes());
            item.setPosting_date(new Date());
            postingRepo.save(item);
            response.setData(item);
            response.setMessage("Item Added Successfully");
            response.setStatus(HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Image Not processable");
            return response;
        }
    }

    public SingleItemDto getItemById(String productId) {
        SingleItemDto singleItemDto = new SingleItemDto();
        Optional<Item> optionalItem = postingRepo.findById(productId);
        if (optionalItem.isPresent()) {
            singleItemDto.setItemDto(optionalItem.get().getItemDto());
        }
        return singleItemDto;
    }

    public GeneralResponse updateItem(String productId, ItemDto itemDto) throws IOException {
        GeneralResponse response = new GeneralResponse();
        Optional<Item> optionalItem = postingRepo.findById(productId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (itemDto.getImg() != null) {
                item.setImg(itemDto.getImg().getBytes());
            }
            item.getItemEntity(itemDto);
            postingRepo.save(item);
            response.setMessage("Item updated Successfully");
            response.setStatus(HttpStatus.OK);
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Item not found!");
        }
        return response;
    }

    public List<ItemDto> getAllItems() {
        return postingRepo.findAll().stream().map(Item::getItemDto).collect(Collectors.toList());
    }

    public GeneralResponse showItem(ItemDto itemDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            Item item = new Item();
            item.getItemEntity(itemDto);
            item.setProduct_id(UUID.randomUUID().toString());
            item.setImg(itemDto.getImg().getBytes());
            item.setPosting_date(new Date());
            response.setData(item);
            response.setMessage("Item Added Successfully");
            response.setStatus(HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage("Image Not processable");
            return response;
        }
    }
}
