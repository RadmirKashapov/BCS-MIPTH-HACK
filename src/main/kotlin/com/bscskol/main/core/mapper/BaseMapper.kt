package com.bscskol.main.core.mapper

import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings

interface BaseMapper<T, K> {

    @Mappings(
            Mapping(target = "id")
    )
    fun convertToDto(entity: T): K

    @Mappings(
            Mapping(target = "id", ignore = true)
    )
    fun convertToModel(dto: K): T

    @Mappings(
            Mapping(target = "id", ignore = true)
    )
    fun updateModelFromDto(dto: K, @MappingTarget entity: T): T

}
