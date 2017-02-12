package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.model.SKS.SksCode;

import java.time.LocalDateTime;
import java.util.UUID;

public class EpisodeOfCareMarker implements DataElement
{
  public EpisodeOfCareMarker()
  {
    id = UUID.randomUUID();
  }

  private UUID id;
  private SksCode code;
  private LocalDateTime time;

  @Override public String getId()
  {
    return id.toString();
  }

  public SksCode getCode()
  {
    return code;
  }

  public void setCode(SksCode code)
  {
    this.code = code;
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public void setTime(LocalDateTime time)
  {
    this.time = time;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EpisodeOfCareMarker episodeOfCareMarker = (EpisodeOfCareMarker) o;

    return id.equals(episodeOfCareMarker.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
