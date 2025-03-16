package ch.pledarigrond.database.user.mappers;

import ch.pledarigrond.common.data.user.RolesDto;
import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.common.data.user.UserForLoginDto;
import ch.pledarigrond.database.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", expression = "java(mapRoles(user))")
    UserDto toUserDto(User user);

    UserForLoginDto toUserForLoginDto(User user);

    default RolesDto mapRoles(User user) {
        RolesDto roles = new RolesDto();
        roles.setRolePuter(user.getRolePuter());
        roles.setRoleRumantschGrischun(user.getRoleRumantschGrischun());
        roles.setRoleSurmiran(user.getRoleSurmiran());
        roles.setRoleSursilvan(user.getRoleSursilvan());
        roles.setRoleSutsilvan(user.getRoleSutsilvan());
        roles.setRoleVallader(user.getRoleVallader());

        roles.setRoleNames(user.getRoleNames());
        roles.setRoleRegistrations(user.getRoleRegistrations());

        return roles;
    }
}
