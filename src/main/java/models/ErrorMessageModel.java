package models;

import lombok.Data;

import java.util.List;

@Data
public class ErrorMessageModel {

    String message;
    List<ErrorsModel> errors;
    String documentation_url;
}
