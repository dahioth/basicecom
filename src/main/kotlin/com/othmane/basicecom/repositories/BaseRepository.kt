package com.othmane.basicecom.repositories

import com.othmane.basicecom.entities.BaseEntity
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<E : BaseEntity> : ListCrudRepository<E, Long>