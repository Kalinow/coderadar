package org.wickedsource.coderadar.core.rest.validation;

import lombok.Getter;

@Getter
public class ValidationException extends UserException {

  private final String field;

  private final String validationMessage;

  public ValidationException(String field, String validationMessage) {
    super("Validation Error");
    this.field = field;
    this.validationMessage = validationMessage;
  }
}
