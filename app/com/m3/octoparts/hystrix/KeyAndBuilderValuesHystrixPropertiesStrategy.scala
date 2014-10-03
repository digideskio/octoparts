package com.m3.octoparts.hystrix

import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.hystrix.{ HystrixCommandKey, HystrixCommandProperties }
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy

/**
 * Custom [[HystrixPropertiesStrategy]] implementation
 */
class KeyAndBuilderValuesHystrixPropertiesStrategy extends HystrixPropertiesStrategy {
  private val mapper = new ObjectMapper()

  /**
   * Overriden to return a [[String]] that is a combination of the commandKey name and a JSON string
   * of the builder values
   */
  override def getCommandPropertiesCacheKey(commandKey: HystrixCommandKey, builder: HystrixCommandProperties.Setter): String =
    s"${commandKey.name()}-${mapper.writeValueAsString(builder)}"

}
