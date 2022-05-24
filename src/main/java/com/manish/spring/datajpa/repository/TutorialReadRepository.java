package com.manish.spring.datajpa.repository;

import com.manish.spring.datajpa.annotations.DataSource;
import com.manish.spring.datajpa.enums.DataSourceType;
import com.manish.spring.datajpa.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialReadRepository extends JpaRepository<Tutorial, Long> {

    @Override
    @DataSource(DataSourceType.SLAVE)
    Tutorial save(Tutorial tutorial);

    @Override
    @DataSource(DataSourceType.SLAVE)
    List<Tutorial> findAll();

    @DataSource(DataSourceType.SLAVE)
    Tutorial findById(long id);

    @DataSource(DataSourceType.SLAVE)
    List<Tutorial> findByPublished(boolean published);

    @DataSource(DataSourceType.SLAVE)
    List<Tutorial> findByTitleContaining(String title);
}
