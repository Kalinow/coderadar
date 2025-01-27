package org.wickedsource.coderadar.metricquery.rest.tree.delta;

import lombok.Data;

@Data
public class Changes {

  private boolean renamed;

  private boolean modified;

  private boolean deleted;

  private boolean added;
}
