package com.lilemy.airobot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 书籍信息
 *
 * @author lilemy
 * @date 2026-11-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布年份
     */
    private Integer publishYear;

    /**
     * 类型
     */
    private List<String> genres;

    /**
     * 简介
     */
    private String description;
}
