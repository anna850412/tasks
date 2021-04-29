package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgesDto {
    @JsonProperty("votes")
    private String votes;
    @JsonProperty("attachmentsByType")
    private AttachmentByTypeDto attachmentByType;
}
