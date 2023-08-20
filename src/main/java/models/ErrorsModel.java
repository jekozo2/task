package models;

import lombok.Data;

@Data
public class ErrorsModel {

    String resource;
    String code;
    String field;
    String message;
}
