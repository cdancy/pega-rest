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

package com.pega.github.rest.features;

import com.pega.github.rest.PegaRestApi;
import com.pega.github.rest.exceptions.TlsSslException;
import com.pega.github.rest.internal.BasePegaRestMockTest;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.testng.annotations.Test;

/**
 * Mock tests for Pega specific exceptions class.
 */
@Test(groups = "unit", testName = "ExceptionsApiMockTest")
public class ExceptionsApiMockTest extends BasePegaRestMockTest {

    /**
     * @throws Exception when TslSsl is enabled but user is using basic auth. 
     */
    @Test(expectedExceptions = TlsSslException.class)
    public void testThrowsTslSslException() throws Exception {
        MockWebServer server = mockEtcdJavaWebServer();

        server.enqueue(new MockResponse().setBody(payloadFromResource("/error.json")).setResponseCode(403));
        PegaRestApi baseApi = api(server.getUrl("/"));
        DocsApi api = baseApi.docsApi();
        try {
            api.docs();
        } finally {
            baseApi.close();
            server.shutdown();
        }
    }
}
