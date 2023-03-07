package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //json 중 null값 데이터가 있으면 버리고, null 아닌 데이터만 받음
public class ResponseUser  {

 private String email;
 private String name;
 private String userId;

 private List<ResponseOrder> order;
 }
