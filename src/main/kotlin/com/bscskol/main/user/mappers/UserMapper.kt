package com.bscskol.main.user.mappers

import com.bscskol.main.core.mapper.BaseMapper
import com.bscskol.main.user.entities.User
import com.bscskol.main.user.vo.UserDTO
import org.mapstruct.*

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
interface UserMapper : BaseMapper<User, UserDTO>
