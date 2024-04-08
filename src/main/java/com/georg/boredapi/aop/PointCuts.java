package com.georg.boredapi.aop;

import org.aspectj.lang.annotation.Pointcut;

/** The type Point cuts. */
public class PointCuts {
  /** All methods. */
  @Pointcut(value = "execution(* com.georg.boredapi.service.*.*(..)) ")
  public void allMethods() {}
}
