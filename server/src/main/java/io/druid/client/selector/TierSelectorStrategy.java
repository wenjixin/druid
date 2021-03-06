/*
 * Druid - a distributed column store.
 * Copyright 2012 - 2015 Metamarkets Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.druid.client.selector;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.druid.timeline.DataSegment;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

/**
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tier", defaultImpl = HighestPriorityTierSelectorStrategy.class)
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "highestPriority", value = HighestPriorityTierSelectorStrategy.class),
    @JsonSubTypes.Type(name = "lowestPriority", value = LowestPriorityTierSelectorStrategy.class),
    @JsonSubTypes.Type(name = "custom", value = CustomTierSelectorStrategy.class)
})
public interface TierSelectorStrategy
{
  public Comparator<Integer> getComparator();

  public QueryableDruidServer pick(TreeMap<Integer, Set<QueryableDruidServer>> prioritizedServers, DataSegment segment);
}
