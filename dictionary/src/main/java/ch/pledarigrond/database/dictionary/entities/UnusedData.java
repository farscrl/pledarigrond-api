package ch.pledarigrond.database.dictionary.entities;

import lombok.Data;

@Data
public class UnusedData {
    private String recId;

    private String rmStatus;
    private String deStatus;

    private String rmSemind;
    private String deSemind;

    private String rmStichwortSortAlpha;
    private String deStichwortSortAlpha;

    private String rmEnum;
    private String deEnum;

    private String deCategories;

    private Integer importedId;
    private String verbId;
}
