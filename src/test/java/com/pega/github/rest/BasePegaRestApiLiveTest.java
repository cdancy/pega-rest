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

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.jclouds.Constants;
import org.jclouds.apis.BaseApiLiveTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

@Test(groups = "live")
public class BasePegaRestApiLiveTest extends BaseApiLiveTest<PegaRestApi> {

    public BasePegaRestApiLiveTest() {
        provider = "pega-rest";
    }

    @Override
    protected Iterable<Module> setupModules() {
        return ImmutableSet.<Module> of(getLoggingModule());
    }

    @Override
    protected Properties setupProperties() {
        Properties overrides = super.setupProperties();
        overrides.setProperty(Constants.PROPERTY_MAX_RETRIES, "0");
        return overrides;
    }

    protected String randomStringLettersOnly() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char randomChar = chars[random.nextInt(chars.length)];
            sb.append(randomChar);
        }
        return sb.toString().toUpperCase();
    }

    protected String randomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
