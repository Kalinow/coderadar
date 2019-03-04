package org.wickedsource.coderadar.graph.domain.filesnapshot;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.wickedsource.coderadar.graph.domain.commit.CommitNode;
import org.wickedsource.coderadar.graph.domain.file.FileNode;
import org.wickedsource.coderadar.graph.domain.metric.MeasuresRelationship;

@NodeEntity(label = "FileSnapshot")
@Getter
@NoArgsConstructor
public class FileSnapshotNode {

  @Setter @GraphId private Long id;

  @Setter private String filepath;

  @Relationship(type = "SNAPSHOT_IN_COMMIT")
  private CommitNode snapshotIn;

  @Relationship(type = "SNAPSHOT_OF_FILE")
  private FileNode snapshotOf;

  @Relationship(type = "MEASURED_BY")
  private Set<MeasuresRelationship> measuredBy;

  public FileSnapshotNode(String filepath) {
    this.filepath = filepath;
  }

  public void snapshotInCommit(CommitNode snapshotIn) {
    this.snapshotIn = snapshotIn;
  }

  public void snapshotOfFile(FileNode snapshotOf) {
    this.snapshotOf = snapshotOf;
  }

  public void measuredBy(Set<MeasuresRelationship> measuredBy) {
    this.measuredBy = measuredBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FileSnapshotNode that = (FileSnapshotNode) o;

    return filepath != null ? filepath.equals(that.filepath) : that.filepath == null;
  }

  @Override
  public int hashCode() {
    return filepath != null ? filepath.hashCode() : 0;
  }

  @Override
  public String toString() {
    return String.format("FileSnapshotNode[filepath=%s]", this.filepath);
  }
}
