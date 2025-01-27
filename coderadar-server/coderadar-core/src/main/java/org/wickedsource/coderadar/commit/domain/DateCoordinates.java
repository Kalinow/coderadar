package org.wickedsource.coderadar.commit.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class DateCoordinates {

  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "month", nullable = false)
  private Integer month;

  @Column(name = "day_of_month", nullable = false)
  private Integer dayOfMonth;

  /**
   * The year to which the week belongs. This is only significant for a week that laps over the end
   * of a year into the next one. Such weeks are either counted to the previous or the next year.
   */
  @Column(name = "week_of_year", nullable = false)
  private Integer weekOfYear;

  @Column(name = "year_of_week", nullable = false)
  private Integer yearOfWeek;

  public DateCoordinates(Date date, Locale locale) {
    updateFromDate(date, locale);
  }

  public void updateFromDate(Date date, Locale locale) {
    Calendar c = Calendar.getInstance(locale);
    c.setTime(date);
    this.dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
    this.year = c.get(Calendar.YEAR);
    this.month = c.get(Calendar.MONTH) + 1;
    this.weekOfYear = c.get(Calendar.WEEK_OF_YEAR);

    if (weekOfYear == 1 && c.get(Calendar.DAY_OF_YEAR) > 7) {
      // it's the first week of the current year!
      this.yearOfWeek = this.year;
    } else if (weekOfYear >= 52 && c.get(Calendar.DAY_OF_YEAR) < 358) {
      // it's the last week of the previous year!
      this.yearOfWeek = this.year - 1;
    } else {
      this.yearOfWeek = this.year;
    }
  }
}
