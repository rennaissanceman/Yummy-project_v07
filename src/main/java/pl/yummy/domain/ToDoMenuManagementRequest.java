package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ToDoMenuManagementRequest {

//    Cel: Obsługa żądań do zarządzania menu restauracji.

//    Przydatne dla właścicieli restauracji, którzy chcą aktualizować swoje menu

    String restaurantName;
    String action; // "ADD", "UPDATE", "DELETE"
    MenuItem menuItem;
}
