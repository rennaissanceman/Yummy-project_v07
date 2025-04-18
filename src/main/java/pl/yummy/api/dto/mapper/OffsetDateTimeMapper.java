package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OffsetDateTimeMapper {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
                .map(odt -> offsetDateTime.atZoneSameInstant(ZoneOffset.UTC))
                .map(odt -> odt.format(DATE_FORMAT))
                .orElse(null);
    }

    @Named("mapStringToOffsetDateTime")
    default OffsetDateTime mapStringToOffsetDateTime(String dateTimeString) {
        return Optional.ofNullable(dateTimeString)
                .map(dt -> OffsetDateTime.parse(dt, DATE_FORMAT.withZone(ZoneOffset.UTC)))
                .orElse(null);
    }
}
