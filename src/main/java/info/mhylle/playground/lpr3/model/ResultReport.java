package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import java.util.List;
import java.util.UUID;

/**
 * Created by mnh on 27-01-2017.
 */
public class ResultReport implements DataElement
{
  private UUID id;
  private SksCode name;
  private SorCode responsibleUnit;
  private SksCode status;

  private List<Result> results;

  public ResultReport()
  {
    this.id = UUID.randomUUID();
  }

  public String getId()
  {
    return id.toString();
  }

  public SksCode getName()
  {
    return name;
  }

  public void setName(SksCode name)
  {
    this.name = name;
  }

  public SorCode getResponsibleUnit()
  {
    return responsibleUnit;
  }

  public void setResponsibleUnit(SorCode responsibleUnit)
  {
    this.responsibleUnit = responsibleUnit;
  }

  public SksCode getStatus()
  {
    return status;
  }

  public void setStatus(SksCode status)
  {
    this.status = status;
  }

  public List<Result> getResults()
  {
    return results;
  }

  public void setResults(List<Result> results)
  {
    this.results = results;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ResultReport resultReport= (ResultReport) o;

    return id.equals(resultReport.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
