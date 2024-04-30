package ch.pledarigrond.common.data.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RolesObject {
    private EditorRole puterRole = EditorRole.NONE;
    private EditorRole rumantschgrischunRole = EditorRole.NONE;
    private EditorRole surmiranRole = EditorRole.NONE;
    private EditorRole sursilvanRole = EditorRole.NONE;
    private EditorRole sutsilvanRole = EditorRole.NONE;
    private EditorRole valladerRole = EditorRole.NONE;

    private EditorRole namesRole = EditorRole.NONE;
    private EditorRole registrationsRole = EditorRole.NONE;

    @JsonIgnore
    public RolesObject getCopy() {
        RolesObject copy = new RolesObject();
        System.out.println("RolesObject getCopy()");

        copy.setPuterRole(getPuterRole());
        copy.setRumantschgrischunRole(getRumantschgrischunRole());
        copy.setSurmiranRole(getSurmiranRole());
        copy.setSursilvanRole(getSursilvanRole());
        copy.setSutsilvanRole(getSutsilvanRole());
        copy.setValladerRole(getValladerRole());

        copy.setNamesRole(getNamesRole());
        copy.setRegistrationsRole(getRegistrationsRole());

        return copy;
    }
}
