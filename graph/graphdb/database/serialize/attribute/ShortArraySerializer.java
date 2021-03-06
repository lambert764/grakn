/*
 * GRAKN.AI - THE KNOWLEDGE GRAPH
 * Copyright (C) 2019 Grakn Labs Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package grakn.core.graph.graphdb.database.serialize.attribute;

import grakn.core.graph.core.attribute.AttributeSerializer;
import grakn.core.graph.diskstorage.ScanBuffer;
import grakn.core.graph.diskstorage.WriteBuffer;
import grakn.core.graph.graphdb.database.serialize.attribute.ArraySerializer;

import java.lang.reflect.Array;

public class ShortArraySerializer extends ArraySerializer implements AttributeSerializer<short[]> {

    @Override
    public short[] convert(Object value) {
        return convertInternal(value, short.class, Short.class);
    }

    @Override
    protected Object getArray(int length) {
        return new short[length];
    }

    @Override
    protected void setArray(Object array, int pos, Object value) {
        Array.setShort(array, pos, (Short) value);
    }

    //############### Serialization ###################

    @Override
    public short[] read(ScanBuffer buffer) {
        int length = getLength(buffer);
        if (length < 0) return null;
        return buffer.getShorts(length);
    }

    @Override
    public void write(WriteBuffer buffer, short[] attribute) {
        writeLength(buffer, attribute);
        if (attribute != null) {
            for (short anAttribute : attribute) {
                buffer.putShort(anAttribute);
            }
        }
    }
}
