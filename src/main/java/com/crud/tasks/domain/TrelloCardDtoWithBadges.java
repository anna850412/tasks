package com.crud.tasks.domain;

import lombok.Data;

@Data
public class TrelloCardDtoWithBadges {
       private String listId;
       private BadgesDto badges;
}
