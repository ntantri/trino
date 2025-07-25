/*
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
package io.trino.plugin.memory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.trino.spi.connector.ColumnMetadata;

import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

public record ColumnInfo(MemoryColumnHandle handle, Optional<String> defaultValue, boolean nullable, Optional<String> comment)
{
    public ColumnInfo
    {
        requireNonNull(handle, "handle is null");
        requireNonNull(defaultValue, "defaultValue is null");
        requireNonNull(comment, "comment is null");
    }

    @JsonIgnore
    public ColumnMetadata getMetadata()
    {
        return ColumnMetadata.builder()
                .setName(handle.name())
                .setType(handle.type())
                .setDefaultValue(defaultValue)
                .setNullable(nullable)
                .setComment(comment)
                .build();
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("name", handle.name())
                .add("type", handle.type())
                .add("defaultValue", defaultValue)
                .add("nullable", nullable)
                .add("comment", comment)
                .toString();
    }
}
