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

package com.palantir.atlasdb.http;

import com.palantir.atlasdb.config.AuxiliaryRemotingParameters;
import com.palantir.conjure.java.api.config.service.UserAgent;

public final class TestProxyUtils {
    public static final AuxiliaryRemotingParameters AUXILIARY_REMOTING_PARAMETERS
            = AuxiliaryRemotingParameters.builder()
                    .shouldLimitPayload(false)
                    .userAgent(UserAgent.of(UserAgent.Agent.of("bla", "0.1.2")))
                    .shouldRetry(true)
                    .build();

    private TestProxyUtils() {
        // constants
    }
}