package info.mhylle.playground.lpr3.model.SKS.condition;

public enum ConditionCode
{
  DI200B("Klinisk vurderet ustabil angina pectoris"),
  DI200C("Ustabil angina pectoris med dokumenteret iskæmi"),
  DM54("Rygsmerter"),
  DM540("Pannikulitis i nakken eller ryggen"),
  DM540A("Pannikulitis i nakken"),
  DM540B("Pannikulitis i ryggen"),
  DM541("Radikulopati UNS"),
  DM541A("Radiculitis lumbosacralis"),
  DM541B("Radiculitis lumbalis"),
  DM541C("Radiculitis brachialis"),
  DM541D("Radiculitis thoracalis"),
  DM542("Cervikale rygsmerter"),
  DM543("Ischias"),
  DM544("Lændesmerter med ischias"),
  DM545("Lændesmerter UNS"),
  DM546("Torakale rygsmerter"),
  DM548("Andre rygsmerter"),
  DM549("Rygsmerter UNS"),
  DR07("Smerter i hals og bryst"),
  DR070("Smerter i svælg"),
  DR071("Smerter i thorax ved vejrtrækning"),
  DR072("Prækordialsmerter"),
  DR073("Andre brystsmerter"),
  DR074("Brystsmerter UNS");

  private String code;

  ConditionCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
