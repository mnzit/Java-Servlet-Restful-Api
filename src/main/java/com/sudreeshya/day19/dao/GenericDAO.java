package com.sudreeshya.day19.dao;

import com.sudreeshya.day19.model.Student;
import java.util.List;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public interface GenericDAO<T> {

    List<T> fetchAll();

    T fetchById(Long id);

    Boolean save(T student);

    Boolean update(T student);

    Boolean deleteById(Long id);
}
