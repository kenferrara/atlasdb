/*
 * (c) Copyright 2020 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.atlasdb.timelock.paxos;

import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.palantir.atlasdb.metrics.Timed;
import com.palantir.common.annotation.Inclusive;
import com.palantir.paxos.PaxosLearner;
import com.palantir.paxos.PaxosValue;

@Path("/" + PaxosTimeLockConstants.INTERNAL_NAMESPACE
        + "/" + PaxosTimeLockConstants.LEADER_PAXOS_NAMESPACE
        + "/learner")
public class LegacyPaxosLearnerShim {
    private final PaxosLearner delegate;

    public LegacyPaxosLearnerShim(PaxosLearner delegate) {
        this.delegate = delegate;
    }

    @POST
    @Path("learn/{seq:.+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public void learn(@PathParam("seq") long seq, PaxosValue val) {
        delegate.learn(seq, val);
    }

    @GET
    @Path("learned-value/{seq:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Optional<PaxosValue> getLearnedValue(@PathParam("seq") long seq) {
        return delegate.getLearnedValue(seq);
    }

    @GET
    @Path("greatest-learned-value")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Optional<PaxosValue> getGreatestLearnedValue() {
        return delegate.getGreatestLearnedValue();
    }

    @GET
    @Path("learned-values-since/{seq:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Collection<PaxosValue> getLearnedValuesSince(@PathParam("seq") @Inclusive long seq) {
        return delegate.getLearnedValuesSince(seq);
    }
}
