package org.wickedsource.coderadar.metricquery.rest.tree.delta;

import lombok.Data;
import org.wickedsource.coderadar.metricquery.rest.tree.MetricValuesSet;
import org.wickedsource.coderadar.metricquery.rest.tree.MetricsTreePayload;

/**
 * Payload class that contains a set of metric values for two commits, thus providing all numbers
 * needed for a delta analysis between two commits.
 */
@Data
public class DeltaTreePayload implements MetricsTreePayload<DeltaTreePayload> {

  private MetricValuesSet commit1Metrics;

  private MetricValuesSet commit2Metrics;

  private String renamedFrom;

  private String renamedTo;

  private Changes changes;

  @Override
  public void add(DeltaTreePayload payload) {}
}
