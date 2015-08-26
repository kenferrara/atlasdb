package com.palantir.atlasdb.keyvalue.partition;

import javax.ws.rs.QueryParam;

import org.assertj.core.util.Preconditions;

import com.palantir.atlasdb.keyvalue.partition.api.PartitionMap;
import com.palantir.atlasdb.keyvalue.partition.util.VersionedObject;

public final class PartitionMapServiceImpl implements PartitionMapService {

    public PartitionMapServiceImpl(PartitionMap partitionMap, long version) {
        Preconditions.checkNotNull(partitionMap);
        this.partitionMap = VersionedObject.of(partitionMap, version);
    }

    VersionedObject<PartitionMap> partitionMap;

    @Override
    public synchronized VersionedObject<PartitionMap> get() {
        return partitionMap;
    }

    @Override
    public synchronized long getVersion() {
        return partitionMap.getVersion();
    }

    @Override
    public synchronized void update(@QueryParam("version") long version, PartitionMap partitionMap) {
        Preconditions.checkNotNull(partitionMap);
        this.partitionMap = VersionedObject.of(partitionMap, version);
    }

}
