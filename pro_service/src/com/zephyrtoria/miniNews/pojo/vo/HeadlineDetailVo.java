package com.zephyrtoria.miniNews.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadlineDetailVo implements Serializable {
     private Integer hid;
     private String title;
     private String article;
     private Integer type;
     private String typeName;
     private Integer pageViews;
     private Long pastHours;
     private Integer publisher;
     private String author;
}
