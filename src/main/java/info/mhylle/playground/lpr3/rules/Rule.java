package info.mhylle.playground.lpr3.rules;

/**
 * Created by mnh on 13-02-2017.
 */
public interface Rule
{
  boolean handle();
  boolean process();
  boolean handle(Object o);
  boolean process(Object o);

}
