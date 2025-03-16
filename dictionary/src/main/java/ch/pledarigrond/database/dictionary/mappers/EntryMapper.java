package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.ExampleDto;
import ch.pledarigrond.common.data.dictionary.inflection.*;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.entities.Example;
import ch.pledarigrond.database.dictionary.entities.inflection.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    EntryDto toEntryDto(Entry entry);
    Entry toEntry(EntryDto entryDto);

    EntryVersionDto toEntryVersionDto(EntryVersion entryVersion);
    EntryVersion toEntryVersion(EntryVersionDto entryDto);

    ExampleDto toExampleDto(Entry entry);
    Example toExample(ExampleDto exampleDto);

    InflectionDto toInflectionDto(Entry entry);
    Inflection toInflection(InflectionDto inflectionDto);

    VerbDto toVerbDto(Entry entry);
    Verb toVerb(VerbDto verbDto);

    NounDto toNounDto(Entry entry);
    Noun toNoun(NounDto nounDto);

    AdjectiveDto toAdjectiveDto(Entry entry);
    Adjective toAdjective(AdjectiveDto adjectiveDto);

    PronounDto toPronounDto(Entry entry);
    Pronoun toPronoun(PronounDto pronounDto);

    OtherDto toOtherDto(Entry entry);
    Other toOther(OtherDto otherDto);
}
