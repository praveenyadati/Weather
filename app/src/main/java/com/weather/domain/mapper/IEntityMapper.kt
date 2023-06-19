package com.weather.domain.mapper

interface IEntityMapper<Entity, Model> {

    fun mapFromEntity(entity: Entity) : Model
}