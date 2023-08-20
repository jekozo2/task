package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonSchemaFilePaths {

    CREATE_REPOSITORY_SCHEMA_FILE_PATH("jsonschems/create_repository_schema.json");

    private final String schemaPath;
}
