package com.grady.server.service;

import com.grady.server.dto.CategoryDto;
import com.grady.server.dto.PageDto;

import java.util.List;

/**
 * @author grady
 * @date 2020 20-7-6 下午4:39
 */
public interface ICategoryService {

    void list(PageDto pageDto);

    void save(CategoryDto categoryDto);

    void delete(String id);

    List<CategoryDto> all();
}
