package com.olamachia.simpleblogappwithdatabinding.util

interface EntityMapper<Entity, Domain> {

    fun mapFromEntity(entity : Entity) : Domain

    fun mapToEntity(domainModel : Domain) : Entity
}