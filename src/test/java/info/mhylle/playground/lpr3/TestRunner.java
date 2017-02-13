package info.mhylle.playground.lpr3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by mnh on 01-02-2017.
 */
public class TestRunner
{

  public static void main(String[] args)
  {
    Result result = JUnitCore.runClasses(RepositoryTest.class, DataGenerator.class);

    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }

    System.out.println(result.wasSuccessful());
  }
}
