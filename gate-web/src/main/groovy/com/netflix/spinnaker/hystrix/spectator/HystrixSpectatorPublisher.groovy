/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.netflix.spinnaker.hystrix.spectator

import com.netflix.hystrix.HystrixCircuitBreaker
import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandKey
import com.netflix.hystrix.HystrixCommandMetrics
import com.netflix.hystrix.HystrixCommandProperties
import com.netflix.hystrix.strategy.HystrixPlugins
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCommand
import com.netflix.spectator.api.ExtendedRegistry

class HystrixSpectatorPublisher extends HystrixMetricsPublisher {
  private final ExtendedRegistry extendedRegistry

  HystrixSpectatorPublisher(ExtendedRegistry extendedRegistry) {
    this.extendedRegistry = extendedRegistry
  }

  @Override
  HystrixMetricsPublisherCommand getMetricsPublisherForCommand(HystrixCommandKey commandKey,
                                                               HystrixCommandGroupKey commandGroupKey,
                                                               HystrixCommandMetrics metrics,
                                                               HystrixCircuitBreaker circuitBreaker,
                                                               HystrixCommandProperties properties) {
    return new HystrixSpectatorPublisherCommand(commandKey, commandGroupKey, metrics, circuitBreaker, properties, extendedRegistry)
  }
}
