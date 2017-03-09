package info.mhylle.playground.lpr3.adaptors;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mnh on 02-02-2017.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>
{

  @Override
  public LocalDateTime unmarshal(String dateString) throws Exception
  {
    try {
      DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
      LocalDateTime parse1 = LocalDateTime.parse(dateString, dtf);
      return parse1;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(LocalDateTime dateTime) throws Exception
  {
    if (dateTime != null) {
      String format = DateTimeFormatter.ISO_DATE_TIME.format(dateTime);
      return format;
    }
    return null;
  }
}
