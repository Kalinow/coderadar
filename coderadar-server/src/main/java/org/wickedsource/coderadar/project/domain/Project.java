package org.wickedsource.coderadar.project.domain;

import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

/** A coderadar project that defines the source of files that are to be analyzed. */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Project {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Embedded private VcsCoordinates vcsCoordinates;

  @Column(nullable = false)
  private String workdirName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VcsCoordinates getVcsCoordinates() {
    return vcsCoordinates;
  }

  public void setVcsCoordinates(VcsCoordinates vcsCoordinates) {
    this.vcsCoordinates = vcsCoordinates;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public String getWorkdirName() {
    return workdirName;
  }

  public void setWorkdirName(String workdirName) {
    this.workdirName = workdirName;
  }
}