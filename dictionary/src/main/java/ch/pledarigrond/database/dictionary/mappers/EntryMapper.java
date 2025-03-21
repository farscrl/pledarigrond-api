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

    ExampleDto toExampleDto(Example example);
    Example toExample(ExampleDto exampleDto);

    InflectionDto toInflectionDto(Inflection inflection);
    Inflection toInflection(InflectionDto inflectionDto);

    VerbDto toVerbDto(Verb verb);
    Verb toVerb(VerbDto verbDto);

    NounDto toNounDto(Noun noun);
    Noun toNoun(NounDto nounDto);

    AdjectiveDto toAdjectiveDto(Adjective adjective);
    Adjective toAdjective(AdjectiveDto adjectiveDto);

    PronounDto toPronounDto(Pronoun pronoun);
    Pronoun toPronoun(PronounDto pronounDto);

    OtherDto toOtherDto(Other other);
    Other toOther(OtherDto otherDto);
}
