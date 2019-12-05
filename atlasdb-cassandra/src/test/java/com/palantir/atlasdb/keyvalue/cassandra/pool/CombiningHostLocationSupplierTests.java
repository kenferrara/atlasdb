/*
 * (c) Copyright 2019 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.atlasdb.keyvalue.cassandra.pool;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

public class CombiningHostLocationSupplierTests {
    private static final Optional<HostLocation> US_1 = Optional.of(HostLocation.of("dc", "us1"));

    @Test
    public void testShouldOverrideHostLocation() {
        HostLocationSupplier hostLocationSupplier = new CombiningHostLocationSupplier(
                () -> US_1,
                () -> {
                    throw new RuntimeException("Should never reach this");
                });

        assertThat(hostLocationSupplier.get()).isEqualTo(US_1);
    }

    @Test
    public void testNoOverride() {
        HostLocationSupplier hostLocationSupplier = new CombiningHostLocationSupplier(Optional::empty, () -> US_1);

        assertThat(hostLocationSupplier.get()).isEqualTo(US_1);
    }
}