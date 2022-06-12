package ua.goit.controller.propertyEditor;

import ua.goit.model.dto.ProducerDto;

import java.beans.PropertyEditorSupport;
import java.util.Objects;
import java.util.UUID;

public class ProducerDtoPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.isBlank()) {
            setValue(null);
            return;
        }
        final String[] split = text.split(",");
        ProducerDto dto = new ProducerDto();
        dto.setId(UUID.fromString(split[0].trim()));
        dto.setName(split[1].trim());
        setValue(dto);
    }
}
