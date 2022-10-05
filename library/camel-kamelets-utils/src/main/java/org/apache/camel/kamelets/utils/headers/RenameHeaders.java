/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.kamelets.utils.headers;


import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Processor;

public class RenameHeaders implements Processor {

    String prefix;
    String renamingPrefix;

    /**
     * Default constructor.
     */
    public RenameHeaders() {
    }

    /**
     * Constructor using fields.
     * @param prefix a prefix to find all the headers to rename.
     * @param renamingPrefix the renaming prefix to use on all the matching headers
     */
    public RenameHeaders(String prefix, String renamingPrefix) {
        this.prefix = prefix;
        this.renamingPrefix = renamingPrefix;
    }

    public void process(Exchange ex) throws InvalidPayloadException {
        Map<String, Object> originalHeaders = ex.getMessage().getHeaders();
        Map<String, Object> modifiedHeaders = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalHeaders.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			if (key.startsWith(prefix)) {
				String newKey = key.replaceFirst(prefix,renamingPrefix);
				String subKey = newKey.substring(renamingPrefix.length());
				String suffix = subKey.replaceAll(
		                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
		                        "(?<=[^A-Z])(?=[A-Z])",
		                        "(?<=[A-Za-z])(?=[^A-Za-z])"), ".").toLowerCase();
				modifiedHeaders.put(renamingPrefix+suffix, val);
			} else {
				modifiedHeaders.put(key, val);
			}
		}
        ex.getMessage().setHeaders(modifiedHeaders);
    }

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setRenamingPrefix(String renamingPrefix) {
		this.renamingPrefix = renamingPrefix;
	}


}
