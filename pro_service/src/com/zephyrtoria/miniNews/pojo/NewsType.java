package com.zephyrtoria.miniNews.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsType implements Serializable {
    private Integer tid;
    private String tname;
}
