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

import java.util.stream.Stream;

import org.citrusframework.common.TestLoader;
import org.citrusframework.junit.jupiter.CitrusSupport;
import org.citrusframework.junit.jupiter.CitrusTestFactory;
import org.citrusframework.junit.jupiter.CitrusTestFactorySupport;
import org.junit.jupiter.api.DynamicTest;

@CitrusSupport
public class KameletsIT {

    @CitrusTestFactory
    public Stream<DynamicTest> avro() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("avro");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> aws() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("aws");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> earthquake() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("earthquake");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> jira() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("jira");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> kafka() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("kafka");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> mail() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("mail");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> openapi() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("openapi");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> protobuf() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("protobuf");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> slack() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("slack");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> timer() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("timer");
    }

    @CitrusTestFactory
    public Stream<DynamicTest> transformation() {
        return CitrusTestFactorySupport.factory(TestLoader.YAML).packageScan("transformation");
    }
}
