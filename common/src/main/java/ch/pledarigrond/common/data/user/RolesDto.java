package ch.pledarigrond.common.data.user;

import ch.pledarigrond.common.data.common.EditorRole;
import lombok.Data;

@Data
public class RolesDto {
    private EditorRole rolePuter = EditorRole.NONE;
    private EditorRole roleRumantschGrischun = EditorRole.NONE;
    private EditorRole roleSurmiran = EditorRole.NONE;
    private EditorRole roleSursilvan = EditorRole.NONE;
    private EditorRole roleSutsilvan = EditorRole.NONE;
    private EditorRole roleVallader = EditorRole.NONE;
    private EditorRole roleNames = EditorRole.NONE;
    private EditorRole roleRegistrations = EditorRole.NONE;
}
