/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pega.github.rest;

import java.net.URI;
import java.util.Properties;

import org.jclouds.apis.ApiMetadata;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
import com.pega.github.rest.config.PegaRestHttpApiModule;

@AutoService(ApiMetadata.class)
public class PegaRestApiMetadata extends BaseHttpApiMetadata<PegaRestApi> {

    public static final String API_VERSION = "v1";
    public static final String BUILD_VERSION = "07.03.00";

    @Override
    public Builder toBuilder() {
        return new Builder().fromApiMetadata(this);
    }

    public PegaRestApiMetadata() {
        this(new Builder());
    }

    protected PegaRestApiMetadata(Builder builder) {
        super(builder);
    }

    public static Properties defaultProperties() {
        Properties properties = BaseHttpApiMetadata.defaultProperties();
        return properties;
    }

    public static class Builder extends BaseHttpApiMetadata.Builder<PegaRestApi, Builder> {

        protected Builder() {
            super(PegaRestApi.class);
            id("pega-rest").name("PegaRest API")
                    .identityName("N/A")
                    .credentialName("N/A")
                    .defaultIdentity("").defaultCredential("")
                    .documentation(URI.create("https://github.com/pegasystems/pega-api/blob/master/pega-api-v1.raml"))
                    .version(API_VERSION).buildVersion(BUILD_VERSION).defaultEndpoint("http://127.0.0.1:8080")
                    .defaultProperties(PegaRestApiMetadata.defaultProperties())
                    .defaultModules(ImmutableSet.<Class<? extends Module>> of(PegaRestHttpApiModule.class));
        }

        @Override
        public PegaRestApiMetadata build() {
            return new PegaRestApiMetadata(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Builder fromApiMetadata(ApiMetadata in) {
            return this;
        }
    }
}
