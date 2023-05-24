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

package org.apache.camel.kamelets.utils.format.converter.aws2.s3;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.apache.camel.kamelets.utils.format.converter.utils.CloudEvents;
import org.apache.camel.kamelets.utils.format.spi.DataTypeConverter;
import org.apache.camel.kamelets.utils.format.spi.annotations.DataType;

/**
 * Output data type represents AWS S3 get object response as CloudEvent V1. The data type sets Camel specific
 * CloudEvent headers on the exchange.
 */
@DataType(scheme = "aws2-s3", name = "application-cloudevents", mediaType = "application/octet-stream")
public class AWS2S3CloudEventOutputType implements DataTypeConverter {

    @Override
    public void convert(Exchange exchange) {
        final Map<String, Object> headers = exchange.getMessage().getHeaders();

        headers.put(CloudEvents.CAMEL_CLOUD_EVENT_ID, exchange.getExchangeId());
        headers.put(CloudEvents.CAMEL_CLOUD_EVENT_TYPE, "org.apache.camel.event.aws.s3.getObject");
        headers.put(CloudEvents.CAMEL_CLOUD_EVENT_SOURCE, "aws.s3.bucket." + exchange.getMessage().getHeader(AWS2S3Constants.BUCKET_NAME, String.class));
        headers.put(CloudEvents.CAMEL_CLOUD_EVENT_SUBJECT, exchange.getMessage().getHeader(AWS2S3Constants.KEY, String.class));
        headers.put(CloudEvents.CAMEL_CLOUD_EVENT_TIME, CloudEvents.getEventTime(exchange));
    }
}
